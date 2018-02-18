/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltraining;

import DAO.Factory;
import Logic.Achievements;
import Logic.Answer;
import Logic.Authusers;
import Logic.Lecture;
import Logic.Question;
import Logic.Users;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static sqltraining.MainMenu.Mac;

/**
 *
 * @author Admin
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    Integer ID = 0;
    ArrayList<Boolean> achivment = new ArrayList<Boolean>();
    ArrayList<Integer> ID_lect = new ArrayList<Integer>();
    List<JButton> buttonList = new ArrayList<JButton>();
    List<JRadioButton> radio = new ArrayList<JRadioButton>();
    List<JCheckBox> checkBox = new ArrayList<JCheckBox>();
    ArrayList<String> nameLect = new ArrayList<String>();
    private static final String DISCLAIMER_CONTENT = "";
    private JScrollPane disclaimerScrollPane;
    private JEditorPane disclaimerContentPane;
    
    
     /***********************QUESTION GENERETE**************************/
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<Integer> IDQuestions = new ArrayList<Integer>();
    ArrayList<String> typeQuestions = new ArrayList<String>();
    ArrayList<Integer> intg = new ArrayList<Integer>();
    ArrayList<Integer> numbersGenerated = new ArrayList<>();
    int ifq = 0 ;
    int btnClick = 0 ;
    Integer idLect = 0;
    /*****************************************************************/
    /************************Answers*********************************/
    ArrayList<String> answerForQ = new ArrayList<String>();
    ArrayList<Boolean> correctAnswer = new ArrayList<Boolean>();
    ArrayList<Boolean> radioIsSelected = new ArrayList<Boolean>();
    ArrayList<Boolean> checkIsSelected = new ArrayList<Boolean>();
    Integer bal = 0;
    
     ArrayList<Integer> newIDUs = new ArrayList<>();
    ArrayList<Integer> EvalForAch = new ArrayList<>();
    ArrayList<Integer> ID_users = new ArrayList<Integer>();
    ArrayList<Integer> ID_uFLect = new ArrayList<Integer>();
    ArrayList<String> userName = new ArrayList<String>();
    ArrayList<String> thisUser = new ArrayList<String>();
    /**************************************************************/
    int jhj = 0 ;
    int statusAch = -1;
    int ID_ach = -1;
    String answerOpen = null;
    String typeQ = "empty";
    String typeU  = "";
    String thisLect = null;
    JCheckBox jCB;
    JRadioButton smallButton;
    JTextField jf;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    boolean first = false;
    String item = "All Users";
    Integer  ItemID = 0;
    Integer  ID_useLect = 0;
    int newID = 0;
    String patch = null;
    String newPatch = null;
    String typeButton = "empty";
    HTMLEditorKit hek;
    HTMLDocument doc;
    String filePath;
    String ImagePath;
    private Action boldAction = new StyledEditorKit.BoldAction();
    private Action underlineAction = new StyledEditorKit.UnderlineAction();
    private Action italicAction = new StyledEditorKit.ItalicAction();
    int[] fontSizes = {6,8,10,12,14,16,20,24,32,36,48,72};
    String[] fontTypes = {"Arial", "Times New Roman", "Monospaced", "Dialog", "DialogInput"};
    
    public MainFrame(String type) {
        initComponents();
        patch = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        newPatch = patch.substring(0, 61);
        thisUser.clear();
        //System.out.println("FullPath "+newPatch);
        leftAlign.setText("");
        centerAlign.setText("");
        rightAlign.setText("");
        bold.setText("");
        under.setText("");
        italic.setText("");
        sizeT.removeAllItems();
        fontType.removeAllItems();
        for(int i = 0; i<fontSizes.length;i++)
        {
            sizeT.addItem(String.valueOf(fontSizes[i]));
        }
        sizeT.setSelectedIndex(0);
        for (int i = 0; i < fontTypes.length;i++)
        {
            fontType.addItem(fontTypes[i]);
        }
        editMenu.setVisible(false);
        comboUser.setVisible(false);
        backButton.setFocusPainted(false);
        questionButton.setVisible(false);
        lectNameLabel.setVisible(false);
        lectNameField.setVisible(false);
        deleteAll.setVisible(false);
        Collection getAllUsers;
        try {
            getAllUsers = Factory.getInstance().getUserDAO().getAllUser();
            Iterator userIT = getAllUsers.iterator();
            while(userIT.hasNext())
            {
                Users users = (Users) userIT.next();
                if(users.getTypeUsers().equals("user"))
                {
                    ID_users.add(users.getIdUsers());
                    userName.add(users.getFirstname()+" "+users.getLastname());
                }   
        }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Collection userForMac = Factory.getInstance().getAuthUserDAO().findByMAC(Mac());
        
        Iterator it = userForMac.iterator();
        while(it.hasNext())
        {
            Authusers authUsers = (Authusers) it.next();
            
            Collection userInfo = Factory.getInstance().getUserDAO().getAuthUserInfo(authUsers);
            Iterator it2 = userInfo.iterator();
            while(it2.hasNext())
            {
                Users users = (Users) it2.next();
                thisUser.add(users.getFirstname()+"_"+users.getLastname());
                uName.setText(users.getFirstname()+" "+users.getLastname());
                typeU = users.getTypeUsers();
                ID =  users.getIdUsers();
            }
        }
        switch(type)
        {
            case "thems":
                nameForFrame.setText("Thems");
                getNameAllLection();
                break;
            case "statistic":
                nameForFrame.setText("Statistic");
                                switch(typeU)
                                {
                                     case "user":
                                                NextButton.setVisible(false);
                                                countText.setVisible(false);
                                                countQuestion.setVisible(false);
                                                dataset.clear();
                                                nameLect.clear();
                                                EvalForAch.clear();
                                                Collection getLect = Factory.getInstance().getLectureDAO().getAllLecture();
                                                Iterator itLect = getLect.iterator();
                                                while(itLect.hasNext())
                                                {
                                                    Lecture lect = (Lecture) itLect.next();
                                                    nameLect.add(lect.getName());

                                                }
                                                Collection  getAchiv = Factory.getInstance().getAchievementsDAO().getAchForUser(ID);
                                                        Iterator itGetAch = getAchiv.iterator();
                                                        while(itGetAch.hasNext())
                                                        {
                                                           Achievements achievments = (Achievements) itGetAch.next();
                                                           EvalForAch.add(achievments.getEval());
                                                        }

                                                for(int h = 0; h<EvalForAch.size();h++)
                                                {
                                                   dataset.setValue(EvalForAch.get(h), " ", ""+nameLect.get(h));
                                                   System.out.println(h+" "+EvalForAch.get(h)+" "); 
                                                }


                                                JFreeChart chart  = ChartFactory.createLineChart("Lection Statistic", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);
                                                CategoryPlot catPlot = chart.getCategoryPlot();
                                                catPlot.setRangeCrosshairPaint(Color.BLUE);

                                                ChartPanel chartPanel = new ChartPanel(chart);
                                                MainPanel.removeAll();
                                                MainPanel.add(chartPanel,BorderLayout.CENTER);
                                                MainPanel.validate();
                                                MainPanel.repaint();
                                        break;
                                     case "admin":
                                            comboUser.addActionListener(comboListener); 
                                            comboUser.setVisible(true);
                                            NextButton.setVisible(false);
                                            countText.setVisible(false);
                                            countQuestion.setVisible(false);
                                            comboUser.removeAllItems();
                                            comboUser.addItem("All users");
                                             for(int i=0;i<userName.size();i++)
                                                {
                                                    comboUser.addItem(userName.get(i));
                                                }
                                            
                                            break;
                                    case "teach":
                                        comboUser.addActionListener(comboListener); 
                                            comboUser.setVisible(true);
                                            NextButton.setVisible(false);
                                            countText.setVisible(false);
                                            countQuestion.setVisible(false);
                                            comboUser.removeAllItems();
                                            comboUser.addItem("All users");
                                             for(int i=0;i<userName.size();i++)
                                                {
                                                    comboUser.addItem(userName.get(i));
                                                }
                                            
                                        break;
                                    
                                     default:
                                        break;
                                         
                                }
                                
        
                                
                break;
            default:
                break;
        }
    }
    
    public void getNameAllLection(){
          NextButton.setVisible(false);
          countText.setVisible(false);
          countQuestion.setVisible(false);
          ID_uFLect.clear();
          Collection getLect = Factory.getInstance().getLectureDAO().getAllLecture();
          Iterator it = getLect.iterator();
          Boolean status = false;
          
          nameLect.clear();
          buttonList.clear();
          achivment.clear();
          while(it.hasNext())
          {
              Lecture lect = (Lecture) it.next();
              ID_lect.add(lect.getIdLecture());
              nameLect.add(lect.getName());
              ID_uFLect.add(lect.getUsers().getIdUsers());
          }
          
          switch(typeU)
          {
              case "admin":
                            MainPanel.removeAll();
                            for(int i = 0; i <nameLect.size(); i++) {
                                    JButton buttons = new JButton(nameLect.get(i),new javax.swing.ImageIcon(getClass().getResource("/res/EditIcon.png")));
                                    buttons.setFocusPainted(false);
                                    buttons.setBackground(new java.awt.Color(236,236,236));
                                    buttons.setForeground(new java.awt.Color(108, 122, 137));
                                    buttons.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    buttons.addActionListener(listener);
                                    buttonList.add(buttons);
                                    MainPanel.add(buttons);
                                }
                            JButton buttons = new JButton("ADD",new javax.swing.ImageIcon(getClass().getResource("/res/AddIcon.png")));
                                    buttons.setBackground(new java.awt.Color(236,236,236));
                                    buttons.setForeground(new java.awt.Color(108, 122, 137));
                                    buttons.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    buttons.setFocusPainted(false);
                                    buttons.addActionListener(addLectionLisener);
                                    buttonList.add(buttons);
                                    MainPanel.add(buttons);
                  break;
              case "user":
                            deleteAll.setVisible(false);
                            MainPanel.removeAll();
                            Collection achThems = Factory.getInstance().getAchievementsDAO().getAchForUser(ID);
                            Iterator iterat = achThems.iterator();
                            while(iterat.hasNext())
                            {
                                Achievements achU = (Achievements) iterat.next();
                                achivment.add(achU.isStatus());
                            }
                            
                            System.out.println(achivment.toString()+" "+nameLect.toString());
                            for(int l=0;l<nameLect.size();l++)
                            {
                                if(achivment.size()<nameLect.size())
                               {
                                   achivment.add(false);
                               }
                            }
                           
                            
                            for(int i = 0; i <nameLect.size(); i++) {
                               
                                if(achivment.get(i) == true)
                                {
                                    JButton button = new JButton(nameLect.get(i),new javax.swing.ImageIcon(getClass().getResource("/res/ok.png")));
                                    button.setFocusPainted(false);
                                    button.setBackground(new java.awt.Color(0,230,64));
                                    button.setForeground(new java.awt.Color(255, 255, 255));
                                    button.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    button.addActionListener(listener);
                                    buttonList.add(button);
                                    MainPanel.add(button);
                                    MainPanel.validate();
                                    MainPanel.repaint();
                                }
                                else
                                {
                                    JButton button = new JButton(nameLect.get(i),new javax.swing.ImageIcon(getClass().getResource("/res/x.png")));
                                    button.setBackground(new java.awt.Color(242,38,19));
                                    button.setForeground(new java.awt.Color(255, 255, 255));
                                    button.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    button.addActionListener(listener);
                                    buttonList.add(button);
                                    MainPanel.add(button);
                                    MainPanel.validate();
                                    MainPanel.repaint();
                                }
                                
                                }
                            
                  break;
            case "teach":
                            MainPanel.removeAll();
                            for(int i = 0; i <nameLect.size(); i++) {
                                if(ID_uFLect.get(i) == ID )
                                {
                                    JButton buttonTeach = new JButton(nameLect.get(i),new javax.swing.ImageIcon(getClass().getResource("/res/EditIcon.png")));
                                    buttonTeach.setFocusPainted(false);
                                    buttonTeach.setBackground(new java.awt.Color(236,236,236));
                                    buttonTeach.setForeground(new java.awt.Color(108, 122, 137));
                                    buttonTeach.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    buttonTeach.addActionListener(listener);
                                    buttonList.add(buttonTeach);
                                    MainPanel.add(buttonTeach);
                                }
                                else
                                {
                                    JButton buttonTeach = new JButton(nameLect.get(i),new javax.swing.ImageIcon(getClass().getResource("/res/showIcon.png")));
                                    buttonTeach.setFocusPainted(false);
                                    buttonTeach.setBackground(new java.awt.Color(236,236,236));
                                    buttonTeach.setForeground(new java.awt.Color(108, 122, 137));
                                    buttonTeach.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    buttonTeach.addActionListener(listener);
                                    buttonList.add(buttonTeach);
                                    MainPanel.add(buttonTeach);
                                }
                            
                                }
                            JButton buttonTeach = new JButton("ADD",new javax.swing.ImageIcon(getClass().getResource("/res/AddIcon.png")));
                                    buttonTeach.setBackground(new java.awt.Color(236,236,236));
                                    buttonTeach.setForeground(new java.awt.Color(108, 122, 137));
                                    buttonTeach.setFont(new java.awt.Font("Tahoma", 1, 14));
                                    buttonTeach.setFocusPainted(false);
                                    buttonTeach.addActionListener(addLectionLisener);
                                    buttonList.add(buttonTeach);
                                    MainPanel.add(buttonTeach);
                break;
              
              default:
                  break;
          }
          MainPanel.setLayout((new FlowLayout())); 
          MainPanel.validate();
          MainPanel.repaint();
          
    }

    public void getLection(){ 
         MainPanel.removeAll();
         MainPanel.setLayout(new BorderLayout(0, 15));
        
            disclaimerContentPane = new JEditorPane(); 
            
            switch(typeU)
            {
              case "admin":
                  disclaimerContentPane.setEditable(true);
                  NextButton.setVisible(false);
                  questionButton.setVisible(true);
                  lectNameLabel.setVisible(true);
                  lectNameField.setVisible(true);
                  deleteAll.setVisible(true);
                  editMenu.setVisible(true);
                  
                  
                  break;
              case "teach":
                            if(typeButton.equals("empty"))
                            {
                             if(ID_useLect == ID )
                             {
                                    disclaimerContentPane.setEditable(true);
                                    NextButton.setVisible(false);
                                    questionButton.setVisible(true);
                                    lectNameLabel.setVisible(true);
                                    lectNameField.setVisible(true);
                                    deleteAll.setVisible(true);
                                    editMenu.setVisible(true);
                             }
                             else
                             {
                                 disclaimerContentPane.setEditable(false);
                                 NextButton.setVisible(false);
                                 deleteAll.setVisible(false);
                                 editMenu.setVisible(false);
                             }
                            }
                            else
                            {
                                    disclaimerContentPane.setEditable(true);
                                    NextButton.setVisible(false);
                                    questionButton.setVisible(true);
                                    lectNameLabel.setVisible(true);
                                    lectNameField.setVisible(true);
                                    deleteAll.setVisible(true);
                                    editMenu.setVisible(true);
                            }
                           
                  break;
               case "user":
                   disclaimerContentPane.setEditable(false);
                   NextButton.setVisible(true);
                   deleteAll.setVisible(false);
                   editMenu.setVisible(false);
                  break;  
               default:
                   break;
            }
            
            disclaimerContentPane.setCaretPosition(0);
            disclaimerContentPane.setContentType("text/html");
            hek = new HTMLEditorKit();
            disclaimerContentPane.setEditorKit(hek);
            doc = (HTMLDocument) disclaimerContentPane.getDocument();
            disclaimerScrollPane = new JScrollPane(disclaimerContentPane);
            disclaimerScrollPane.setPreferredSize(new Dimension(800, 450));

            MainPanel.add(disclaimerScrollPane, BorderLayout.CENTER);  
            MainPanel.validate();
            MainPanel.repaint();
    }
     
    ActionListener listener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                for(int i = 0; i<buttonList.size();i++)
                {
                    if(e.getSource() == buttonList.get(i))
                    {
                        idLect = ID_lect.get(i);
                        System.out.println("ID LECT " + idLect);
                         Collection lectText = Factory.getInstance().getLectureDAO().getLectureForID(idLect);
                          Iterator it = lectText.iterator();
                            while(it.hasNext())
                             {
                                 Lecture lect = (Lecture) it.next();
                                 ID_useLect = lect.getUsers().getIdUsers();
                                 thisLect = lect.getLecture();
                                 getLection();
                                 File file = new File(newPatch+"src\\res\\Lecture\\"+thisLect);
                                 lectNameField.setText(lect.getName());
                            try {
                                disclaimerContentPane.setPage(file.toURI().toURL());
                                //deleteAll.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                             }
                            
                    } 
                }
            }
        }

    };
    
    ActionListener addLectionLisener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            typeButton = "ADD";
            getLection();
            deleteAll.setVisible(false);
            lectNameField.setText("Lection "+(ID_lect.size()+1));
            disclaimerContentPane.setText("<h3>New Lection</h3>"); 
            
        }
    };
    
    ActionListener comboListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                        JComboBox box = (JComboBox)e.getSource();
                        String item = (String)box.getSelectedItem();
//                        System.out.println(item);
                        EvalForAch.clear();
                        nameLect.clear();
                        newID = box.getSelectedIndex()-1;
                        dataset.clear();
//                        System.out.println(newID);
                        Collection getLect = Factory.getInstance().getLectureDAO().getAllLecture();
                        Iterator itLect = getLect.iterator();
                            while(itLect.hasNext())
                                {
                                    Lecture lect = (Lecture) itLect.next();
                                    nameLect.add(lect.getName());
                                }
                        
                        if(newID >= 0)
                        {
                            switch(typeU)
                            {
                                case "admin":
                                                Collection  getAchiv = Factory.getInstance().getAchievementsDAO().getAchForUser(ID_users.get(newID));
                                                Iterator itGetAch = getAchiv.iterator();
                                                while(itGetAch.hasNext())
                                                    {
                                                        Achievements achievments = (Achievements) itGetAch.next();
                                                        EvalForAch.add(achievments.getEval());
                                                    }
                                                for(int f=0;f<EvalForAch.size();f++)
                                                {
                                                    dataset.setValue(EvalForAch.get(f), " ", " "+nameLect.get(f));
                                                }
                                                 System.out.println(EvalForAch.toString());
                                                JFreeChart chart  = ChartFactory.createLineChart("Lection Statistic: "+item, "Lection", "Evals", dataset, PlotOrientation.VERTICAL, false, false, false);
                                                CategoryPlot catPlot = chart.getCategoryPlot();
                                                catPlot.setRangeCrosshairPaint(Color.BLUE);

                                                ChartPanel chartPanel = new ChartPanel(chart);
                                                MainPanel.removeAll();
                                                MainPanel.add(chartPanel,BorderLayout.CENTER);
                                                MainPanel.validate();
                                    break;
                                case "teach":
                                                Collection Lect = Factory.getInstance().getLectureDAO().getLectureForIDUser(ID);
                                                Iterator lIt = Lect.iterator();
                                                while(lIt.hasNext())
                                                {
                                                    Lecture lection = (Lecture) lIt.next();
                                                    
                                                    Collection achiv = Factory.getInstance().getAchievementsDAO().getAchForUserAndLect(lection.getIdLecture(), ID_users.get(newID));
                                                    Iterator itAch = achiv.iterator();
                                                    while(itAch.hasNext())
                                                    {
                                                        Achievements achivs = (Achievements) itAch.next();
                                                        dataset.setValue(achivs.getEval(), " ", " "+lection.getName());
                                                        
                                                    }
                                                }
                                                 
                                                JFreeChart charts  = ChartFactory.createLineChart("Lection Statistic: "+item, "Lection", "Evals", dataset, PlotOrientation.VERTICAL, false, false, false);
                                                CategoryPlot catPlots = charts.getCategoryPlot();
                                                catPlots.setRangeCrosshairPaint(Color.BLUE);

                                                ChartPanel chartPanels = new ChartPanel(charts);
                                                MainPanel.removeAll();
                                                MainPanel.add(chartPanels,BorderLayout.CENTER);
                                                MainPanel.validate();
                                    break;
                                default:
                                    break;
                            }
                            

                        }
                        else
                        {
                           Collection getAllUsers;
                    try {
                        getAllUsers = Factory.getInstance().getUserDAO().getAllUser();
                        Iterator allU = getAllUsers.iterator();
                           while(allU.hasNext())
                            {
                                Users us = (Users)allU.next();
                                if(us.getTypeUsers().equals("user"))
                                   {
                                        Collection getAll = Factory.getInstance().getLectureDAO().getAllLecture();
                                        Iterator allLect = getAll.iterator();
                                            while(allLect.hasNext())
                                                {
                                                    Lecture lects = (Lecture) allLect.next();
                                                     Collection  getAchiv = Factory.getInstance().getAchievementsDAO().getAchForUserAndLect(lects.getIdLecture(), us.getIdUsers());
                                                        Iterator itGetAch = getAchiv.iterator();
                                                        while(itGetAch.hasNext())
                                                            {
                                                                Achievements achievments = (Achievements) itGetAch.next();
                                                                
                                                                dataset.setValue(achievments.getEval(), " "+us.getFirstname()+" "+us.getLastname(), " "+lects.getName());
                                                               // System.out.println(us.getFirstname()+" "+us.getLastname()+" "+lects.getName()+" "+achievments.getEval());
                                                            }

                                                    
                                                }
                                   }   
                            }
                    } catch (SQLException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            JFreeChart chart  = ChartFactory.createLineChart("Lection Statistic: "+item, "Lection", "Evals", dataset, PlotOrientation.VERTICAL, true, false, false);
                            CategoryPlot catPlot = chart.getCategoryPlot();
                            catPlot.setRangeCrosshairPaint(Color.BLUE);

                            ChartPanel chartPanel = new ChartPanel(chart);
                            MainPanel.removeAll();
                            MainPanel.add(chartPanel,BorderLayout.CENTER);
                            MainPanel.validate();
                        }
                        
                       
            }
     };
    
    public void panelForQuestions(){
          String typeQuest = "";
          questions.clear();
          intg.clear();
          numbersGenerated.clear();
          Collection listQues = Factory.getInstance().getQuestionDAO().getQuestionForID(idLect);
          Iterator it = listQues.iterator();
          while(it.hasNext())
          {
              Question question = (Question) it.next();
              IDQuestions.add(question.getIdQuestions());
              questions.add(question.getQuestions());
              typeQuestions.add(question.getType());
          }
          
          for(int i = 0; i<questions.size(); i++)
          {
              questions.get(i);
              intg.add(i);
              
          }
          if(!intg.isEmpty())
          {
              for (int i = 0; i < 5; i++) {
              Random randNumber = new Random();
              int iNumber = randNumber.nextInt(intg.size());

              if (!numbersGenerated.contains(iNumber)) {
                  numbersGenerated.add(iNumber);
              } else {
                  i--;
              }
                }
              
              //System.out.println(numbersGenerated.toString());
          }
              
    }
    
    public void QuestionAndAnswer(){
                MainPanel.removeAll();
                MainPanel.setLayout(new GridLayout(6,4));
                answerForQ.clear();
                correctAnswer.clear();
                
                int max = numbersGenerated.size()-1;

                System.out.println(max+"   "+ifq);
                Integer h = numbersGenerated.get(ifq);
                
                if(ifq>=max)
                {
                    //System.out.println("Stop");
                }
                else
                {
                    ifq = ifq+1;
                }
                countText.setText("0/5");
                countText.setVisible(true);
                countQuestion.setVisible(true);
                countQuestion.setMinimum(0);
                countQuestion.setMaximum(5);

                Collection answers = Factory.getInstance().getAnswerDAO().getAnswerForIDQ(IDQuestions.get(h));
                Iterator it = answers.iterator();
                  while(it.hasNext())
                  {
                          Answer answ = (Answer) it.next();
                          answerForQ.add(answ.getAnswer());
                          correctAnswer.add(answ.isCorrect());
                          
                  }
                
                System.out.println(answerForQ.toString()+" | "+correctAnswer.toString());
                int value = countQuestion.getValue() + 1;
                      int maximum = countQuestion.getMaximum();
                      if(value > maximum) {
                          value = maximum;
                      }
                countText.setText(value+"/5");
                countQuestion.setValue(value);
               
                
                int size = 200;
        
                StringBuffer sb = new StringBuffer(questions.get(h));
                sb.insert(0, "<html>");
                sb.insert(sb.length(), "</html>");


                double fSize = ((sb.length()-1)/size);

                for(double i=0; i<fSize; i++)
                {
                    System.out.println(i);

                    sb.insert(size, "<br>");
                    size = size+200;
                }
                
                Font font = new Font("Tahoma",1, 18);
                JLabel jl = new JLabel();
                jl.setForeground(Color.WHITE);
                //jl.setText("  "+questions.get(h));
                jl.setText(sb.toString());
                jl.setFont(font);
                jl.setSize(30, 30);
                JLabel questionIcon = new JLabel();
                jl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/qIcon.png")));
                jl.setSize(30, 30);
                MainPanel.add(jl);
                switch(typeQuestions.get(h))
                {
                   case "oneanswer":
                                        radioIsSelected.clear();
                                        radio.clear();
                                        ButtonGroup group = new ButtonGroup();
                                        for(int i=0;i<answerForQ.size();i++)
                                        {
                                            smallButton = new JRadioButton(answerForQ.get(i));
                                            smallButton.setBackground(new java.awt.Color(44, 62, 80));
                                            smallButton.setForeground(Color.WHITE);
                                            smallButton.setFont(new java.awt.Font("Tahoma", 1, 14));
                                            //smallButton
                                            group.add(smallButton);
                                            radio.add(smallButton);
                                            MainPanel.add(smallButton);
                                            MainPanel.validate();
                                            MainPanel.repaint();
                                            
                                            smallButton.addItemListener(new ItemListener() {
                                                
                                              @Override
                                              public void itemStateChanged(ItemEvent e) {
                                                  //radioIsSelected.clear();
                                                  //System.out.println(radioIsSelected.toString());
                                                  for(int j = 0; j <radio.size();j++ )
                                                  {
                                                      radioIsSelected.add(radio.get(j).isSelected());
                                                  }
                                              }
                                          });
                                        }
                                       typeQ = "oneanswer";
                                        
                       break;
                   case "manyanswer":
                                       
                                        checkBox.clear();
                                        for(int i=0;i<answerForQ.size();i++)
                                        {
                                            jCB = new JCheckBox(answerForQ.get(i));
                                            checkBox.add(jCB);
                                            jCB.setBackground(new java.awt.Color(44, 62, 80));
                                            jCB.setForeground(Color.WHITE);
                                            jCB.setFont(new java.awt.Font("Tahoma", 1, 14));
                                            MainPanel.add(jCB);
                                            MainPanel.validate();
                                            MainPanel.repaint();
                                            
                                           
                                            jCB.addItemListener(new ItemListener() {

                                              @Override
                                              public void itemStateChanged(ItemEvent e) {
                                                  checkIsSelected.clear();
                                                  
                                                  for(int k = 0; k<checkBox.size();k++)
                                                  {
                                                      checkIsSelected.add(checkBox.get(k).isSelected());
                                                  }
                                                  //System.out.println(checkIsSelected.toString());
                                              }
                                          });
                                        }
                                        typeQ = "manyanswer";
                                        
                       break;
                   case "openanswer":
                                        jf = new JTextField();
                                        jf.setBackground(new java.awt.Color(44, 62, 80));
                                        jf.setForeground(new java.awt.Color(228, 241, 254));
                                        jf.setFont(new java.awt.Font("Tahoma", 1, 14));
                                        jf.setSize(50, 100);
                                        MainPanel.add(jf).requestFocusInWindow();
                                        MainPanel.validate();
                                        MainPanel.repaint();
                                        jf.addFocusListener(new java.awt.event.FocusAdapter() {
                                            public void focusLost(java.awt.event.FocusEvent evt) {
                                                answerOpen = jf.getText();
                                            }
                                        });
                                        typeQ = "openanswer";
                       break;
                   default:
                       break;
               }
                
              /*****************************************/
              MainPanel.validate();
              MainPanel.repaint();
              /****************************************/
              
      }  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        uName = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        NextButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        questionButton = new javax.swing.JButton();
        deleteAll = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        countText = new javax.swing.JLabel();
        countQuestion = new javax.swing.JProgressBar();
        lectNameLabel = new javax.swing.JLabel();
        lectNameField = new javax.swing.JTextField();
        editMenu = new javax.swing.JToolBar();
        fontType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        sizeT = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        leftAlign = new javax.swing.JButton(new StyledEditorKit.AlignmentAction("Left Align",StyleConstants.ALIGN_LEFT));
        centerAlign = new javax.swing.JButton(new StyledEditorKit.AlignmentAction("Center",StyleConstants.ALIGN_CENTER));
        rightAlign = new javax.swing.JButton(new StyledEditorKit.AlignmentAction ("Right Align",StyleConstants.ALIGN_RIGHT));
        bold = new javax.swing.JButton(boldAction);
        under = new javax.swing.JButton(underlineAction);
        italic = new javax.swing.JButton(italicAction);
        addImage = new javax.swing.JButton();
        comboUser = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        nameForFrame = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setUndecorated(true);
        setResizable(false);

        topPanel.setBackground(new java.awt.Color(44, 62, 80));

        icon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Desktop\\MainIcon.png")); // NOI18N
        icon.setEnabled(false);

        uName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        uName.setForeground(new java.awt.Color(255, 255, 255));
        uName.setText("Username");

        MainPanel.setBackground(new java.awt.Color(44, 62, 80));
        MainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        MainPanel.setMaximumSize(new java.awt.Dimension(873, 492));
        MainPanel.setPreferredSize(new java.awt.Dimension(870, 500));
        MainPanel.setLayout(new javax.swing.BoxLayout(MainPanel, javax.swing.BoxLayout.LINE_AXIS));

        NextButton.setBackground(new java.awt.Color(34, 167, 240));
        NextButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NextButton.setForeground(new java.awt.Color(255, 255, 255));
        NextButton.setText("Next>");
        NextButton.setBorderPainted(false);
        NextButton.setFocusable(false);
        NextButton.setMaximumSize(new java.awt.Dimension(120, 36));
        NextButton.setMinimumSize(new java.awt.Dimension(120, 36));
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(242, 38, 19));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("<Back");
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        backButton.setPreferredSize(new java.awt.Dimension(120, 36));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        questionButton.setBackground(new java.awt.Color(34, 167, 240));
        questionButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        questionButton.setForeground(new java.awt.Color(255, 255, 255));
        questionButton.setText("Questions");
        questionButton.setBorderPainted(false);
        questionButton.setFocusable(false);
        questionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionButtonActionPerformed(evt);
            }
        });

        deleteAll.setBackground(new java.awt.Color(242, 38, 19));
        deleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Basket.png"))); // NOI18N
        deleteAll.setBorderPainted(false);
        deleteAll.setFocusPainted(false);
        deleteAll.setFocusable(false);
        deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        countText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        countText.setForeground(new java.awt.Color(255, 255, 255));
        countText.setText("0/0");

        countQuestion.setForeground(new java.awt.Color(34, 167, 240));

        lectNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lectNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lectNameLabel.setText("Lection name:");

        lectNameField.setBackground(new java.awt.Color(108, 122, 137));
        lectNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lectNameField.setForeground(new java.awt.Color(228, 241, 254));
        lectNameField.setMaximumSize(new java.awt.Dimension(100, 25));
        lectNameField.setMinimumSize(new java.awt.Dimension(100, 25));
        lectNameField.setPreferredSize(new java.awt.Dimension(130, 25));

        editMenu.setBackground(new java.awt.Color(44, 62, 80));
        editMenu.setFloatable(false);
        editMenu.setRollover(true);
        editMenu.setBorderPainted(false);
        editMenu.setPreferredSize(new java.awt.Dimension(95, 25));

        fontType.setBackground(new java.awt.Color(108, 122, 137));
        fontType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fontType.setForeground(new java.awt.Color(255, 255, 255));
        fontType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fontType.setFocusable(false);
        fontType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontTypeActionPerformed(evt);
            }
        });
        editMenu.add(fontType);

        jLabel1.setText("     ");
        editMenu.add(jLabel1);

        sizeT.setBackground(new java.awt.Color(108, 122, 137));
        sizeT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sizeT.setForeground(new java.awt.Color(255, 255, 255));
        sizeT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sizeT.setFocusable(false);
        sizeT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeTActionPerformed(evt);
            }
        });
        editMenu.add(sizeT);

        jLabel2.setText("        ");
        editMenu.add(jLabel2);

        leftAlign.setBackground(new java.awt.Color(108, 122, 137));
        leftAlign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AlignLeftHK.png"))); // NOI18N
        leftAlign.setBorderPainted(false);
        leftAlign.setFocusPainted(false);
        leftAlign.setFocusable(false);
        leftAlign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        leftAlign.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(leftAlign);

        centerAlign.setBackground(new java.awt.Color(108, 122, 137));
        centerAlign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AlignCenterHK.png"))); // NOI18N
        centerAlign.setBorderPainted(false);
        centerAlign.setFocusPainted(false);
        centerAlign.setFocusable(false);
        centerAlign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        centerAlign.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(centerAlign);

        rightAlign.setBackground(new java.awt.Color(108, 122, 137));
        rightAlign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/AlignRightHK.png"))); // NOI18N
        rightAlign.setBorderPainted(false);
        rightAlign.setFocusPainted(false);
        rightAlign.setFocusable(false);
        rightAlign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rightAlign.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(rightAlign);

        bold.setBackground(new java.awt.Color(108, 122, 137));
        bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bold.png"))); // NOI18N
        bold.setBorderPainted(false);
        bold.setFocusPainted(false);
        bold.setFocusable(false);
        bold.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bold.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(bold);

        under.setBackground(new java.awt.Color(108, 122, 137));
        under.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/under.png"))); // NOI18N
        under.setBorderPainted(false);
        under.setFocusPainted(false);
        under.setFocusable(false);
        under.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        under.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(under);

        italic.setBackground(new java.awt.Color(108, 122, 137));
        italic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/italic.png"))); // NOI18N
        italic.setBorderPainted(false);
        italic.setFocusPainted(false);
        italic.setFocusable(false);
        italic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        italic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editMenu.add(italic);

        addImage.setBackground(new java.awt.Color(108, 122, 137));
        addImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/imageIcon.png"))); // NOI18N
        addImage.setToolTipText("");
        addImage.setBorderPainted(false);
        addImage.setFocusPainted(false);
        addImage.setFocusable(false);
        addImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addImage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageActionPerformed(evt);
            }
        });
        editMenu.add(addImage);

        comboUser.setBackground(new java.awt.Color(34, 167, 240));
        comboUser.setForeground(new java.awt.Color(255, 255, 255));
        comboUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lectNameLabel)
                .addGap(170, 170, 170)
                .addComponent(comboUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addComponent(lectNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(90, 90, 90)
                            .addComponent(countText)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(countQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(editMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lectNameLabel)
                    .addComponent(comboUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lectNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(countText)
                        .addComponent(countQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(editMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(deleteAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(icon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(icon)
                            .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(questionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(deleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        nameForFrame.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        nameForFrame.setForeground(new java.awt.Color(255, 255, 255));
        nameForFrame.setText("           ");

        jButton1.setBackground(new java.awt.Color(248, 148, 6));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(248, 148, 6));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("_");
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(nameForFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(nameForFrame)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
            this.dispose();
            MainMenu mM = new MainMenu();
            mM.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        questions.clear();
        Collection listQues = Factory.getInstance().getQuestionDAO().getQuestionForID(idLect);
          Iterator itQ = listQues.iterator();
          while(itQ.hasNext())
          {
              Question question = (Question) itQ.next();
              questions.add(question.getQuestions());
          }
        
          if(questions.size()<5)
          {
              JOptionPane.showMessageDialog(null, "Lecture is not ready", "Message", JOptionPane.ERROR_MESSAGE);
          }
        else
          {
        if(first == false)
        {
           panelForQuestions();
           first = true;
        }
        else
        {        
        btnClick = btnClick +1;
        
        switch(typeQ)
        {
            case "empty":
                break;
                
            case "oneanswer":
                                if(!correctAnswer.isEmpty() & !radioIsSelected.isEmpty())
                                    {
                                        if(correctAnswer.equals(radioIsSelected))
                                       {
                                           System.out.println("OK");
                                           jhj = jhj+1;
                                       }
                                        else
                                        {
                                            System.out.println("NOOOO");
                                        }
                                    }
                                    //System.out.println("Points "+jhj);
                                    
                break;
            case "manyanswer":
                                if(!correctAnswer.isEmpty() & !checkIsSelected.isEmpty())
                                    {
                                        for(int i=0;i<correctAnswer.size();i++)
                                        {

                                            if(correctAnswer.get(i) == true & checkIsSelected.get(i) == true)
                                            {
                                                System.out.println("OK");
                                                jhj = jhj+1;
                                            }
                                             else
                                             {
                                                 System.out.println("NOT");
                                             }
                                        }
                                        
                                    }
                                 System.out.println("Points "+jhj);
                                System.out.println("Correct "+correctAnswer.toString()+" | "+checkIsSelected.toString());
                break;
            case "openanswer":
                                    //System.out.println(answerOpen.equals(answerForQ.toString()));
                                    if(answerForQ.get(0).equalsIgnoreCase(answerOpen))
                                        {
                                             System.out.println("OOOOKKKK");
                                             jhj = jhj+1;
                                        }
                                     System.out.println("Points "+jhj);
                break;
            default:
                break;
        }
        if(btnClick>5)
        {
            Collection getAchFU = Factory.getInstance().getAchievementsDAO().getAchForUserAndLect(idLect, ID);
            Iterator it = getAchFU.iterator();
            
            while(it.hasNext())
            {
                Achievements achU = (Achievements) it.next();
                ID_ach = achU.getIdAchievements();
                statusAch = achU.getEval();
                
            }
           JOptionPane.showMessageDialog(null, "You got: "+jhj+" points", "Information", JOptionPane.INFORMATION_MESSAGE);
           System.out.println("SSSSSSSS "+statusAch+" | "+ID_ach);
            if(statusAch >= 0)
            {
                System.out.println("Update");
                Factory.getInstance().getAchievementsDAO().updateAchievements(ID_ach,jhj,true);
                
                jhj = 0;
                
            }
            else
            {
               System.out.println("ADD");
               Lecture lect = new Lecture();
               lect.setIdLecture(idLect);
               Users us = new Users();
               us.setIdUsers(ID);
               Achievements addAch = new Achievements();
               addAch.setUsers(us);
               addAch.setEval(jhj);
               addAch.setLecture(lect);
               addAch.setStatus(true);
               
               Factory.getInstance().getAchievementsDAO().Add(addAch);
            }
            btnClick = 0 ;
            first = false;
            typeQ = "empty";
            
            //System.out.println(btnClick+" | "+first+" | "+typeQ);
            
            getNameAllLection();
            countQuestion.setValue(0);
            ifq = 0;
        }
        else
        {
             QuestionAndAnswer();
        }
       
        }
          }
    }//GEN-LAST:event_NextButtonActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
         this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void questionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionButtonActionPerformed
            String f = "";
            String name = lectNameField.getText();
            String userFolder = newPatch+"src\\res\\Lecture\\"+thisUser.get(0);
            switch(typeButton)
            {
                case "empty":
                    System.out.println(disclaimerContentPane.getText());
                    Factory.getInstance().getLectureDAO().updateLecture(idLect,name,thisLect);
                    try{
                        FileWriter fw = new FileWriter(newPatch+"src\\res\\Lecture\\"+thisLect);
				fw.write(disclaimerContentPane.getText());
				fw.close();
			}catch(FileNotFoundException fnfe){
				System.err.println("FileNotFoundException: " + fnfe.getMessage());			
			}catch(IOException ioe){
				System.err.println("IOException: " + ioe.getMessage());
			}	                    
                    break;
                case "ADD":
                    Users usID = new Users();
                    usID.setIdUsers(ID);
                    Lecture lect = new Lecture();
                    lect.setName(name);
                    lect.setLecture(thisUser.get(0)+"\\"+name+".html");
                    lect.setUsers(usID);
                    Factory.getInstance().getLectureDAO().Add(lect);
                    idLect = lect.getIdLecture();
//                    System.out.println(idLect);
                    
                      File dir = new File(userFolder);
                        if(dir.exists())
                         {
                            File newDir = new File(userFolder+"\\Resources");
                                if(!newDir.exists())
                                {
                                    newDir.mkdir();
                                } 
                            File lection = new File(userFolder+"\\"+name+".html");
                            if(!lection.exists())
                             {
                               try {
                                     lection.createNewFile();
                                     try{
                                        FileWriter fw = new FileWriter(lection);
                                                fw.write(disclaimerContentPane.getText());
                                                fw.close();
                                        }catch(FileNotFoundException fnfe){
                                                System.err.println("FileNotFoundException: " + fnfe.getMessage());			
                                        }catch(IOException ioe){
                                                System.err.println("IOException: " + ioe.getMessage());
                                        }
                                     
                               } catch (IOException ex) {
                                   Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                              }
                            }
                        else
                           {
                                dir.mkdir();
                                File newDir = new File(userFolder+"\\Resources");
                                if(!newDir.exists())
                                {
                                    newDir.mkdir();
                                } 
                                File lection = new File(userFolder+"\\"+name+".html");
                                if(!lection.exists())
                                {
                                     try {
                                        lection.createNewFile();
                                        try{
                                        FileWriter fw = new FileWriter(lection);
                                                fw.write(disclaimerContentPane.getText());
                                                fw.close();
                                        }catch(FileNotFoundException fnfe){
                                                System.err.println("FileNotFoundException: " + fnfe.getMessage());			
                                        }catch(IOException ioe){
                                                System.err.println("IOException: " + ioe.getMessage());
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                    break;
            }
            this.dispose();
            
            QuestionFrame tf = new QuestionFrame(idLect);
            tf.setVisible(true);
            /*System.out.println("ID - "+idLect);
            System.out.println(f);*/
    }//GEN-LAST:event_questionButtonActionPerformed

    private void deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllActionPerformed
              
        Lecture lect = new Lecture();
        lect.setIdLecture(idLect);
        
        Factory.getInstance().getLectureDAO().DeleteLection(lect);
        this.dispose();
        File lectur = new File(newPatch+"src\\res\\Lecture\\"+thisLect);
        if(lectur.exists())
        {
            lectur.delete();
        }
            
        JOptionPane.showMessageDialog(null,lectNameField.getText()+" deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
        
        MainMenu mM = new MainMenu();
        mM.setVisible(true);
        
        
    }//GEN-LAST:event_deleteAllActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fontTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontTypeActionPerformed
        int indexT = 0;  
        JComboBox source = (JComboBox) evt.getSource(); 
        String item = (String) source.getSelectedItem();
        int indexs = source.getSelectedIndex();
        System.out.println(item);

        if(indexT != -1)
        {
              fontType.setAction(new StyledEditorKit.FontFamilyAction(item,item));
        }
    }//GEN-LAST:event_fontTypeActionPerformed

    private void sizeTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeTActionPerformed
        JComboBox source = (JComboBox) evt.getSource();
        String size1 = (String) source.getSelectedItem();
        
        //System.out.println("String "+size1);
        if(size1 != null)
        {
            Integer size = Integer.parseInt(size1);
            //System.out.println("Int "+size);
            Action fontAction = new StyledEditorKit.FontSizeAction(size1, size);
            fontAction.actionPerformed(evt);
        }
    }//GEN-LAST:event_sizeTActionPerformed

public static void copy(File source, File dest) throws IOException {
        FileInputStream is = new FileInputStream(source);
        try {
            FileOutputStream os = new FileOutputStream(dest);
            try {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } finally {
                os.close();
            }
        } finally {
            is.close();
        }
    }    
       
    private void addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageActionPerformed
        HTMLDocument doc = (HTMLDocument) disclaimerContentPane.getDocument();
            HTMLEditorKit kit = (HTMLEditorKit) disclaimerContentPane.getEditorKit();
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
                return;
             String dr = newPatch+"src\\res\\Lecture\\"+thisUser.get(0)+"\\Resources\\";
             ImagePath=fc.getSelectedFile().getPath();
             File source = new File(ImagePath);
             File dest = new File(dr+fc.getSelectedFile().getName());
        try {
            copy(source,dest);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
             String newFp = dr+fc.getSelectedFile().getName();
             char[] chArray = newFp.toCharArray();
                //перебираем все элементы массива
                for(int i = 0; i<chArray.length; i++){
                //находим пробел	
                if(chArray[i] == '\\'){
                //заменяем на точку
		chArray[i] = '/';
	
                    }	
              }
             String res = String.valueOf(chArray);
             
             System.out.println(res);
             String preTag="<PRE>filename is : "+res+"</PRE>";
             String imageTag="<img src= file:\""+res+"\">";
             
             
             System.out.println(imageTag);
             
             
            try {
                kit.insertHTML(doc, doc.getLength(), preTag+imageTag, 0, 0, HTML.Tag.IMG);
            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_addImageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton addImage;
    private javax.swing.JButton backButton;
    private javax.swing.JButton bold;
    private javax.swing.JButton centerAlign;
    private javax.swing.JComboBox<String> comboUser;
    private javax.swing.JProgressBar countQuestion;
    private javax.swing.JLabel countText;
    private javax.swing.JButton deleteAll;
    private javax.swing.JToolBar editMenu;
    private javax.swing.JComboBox<String> fontType;
    private javax.swing.JLabel icon;
    private javax.swing.JButton italic;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lectNameField;
    private javax.swing.JLabel lectNameLabel;
    private javax.swing.JButton leftAlign;
    private javax.swing.JLabel nameForFrame;
    private javax.swing.JButton questionButton;
    private javax.swing.JButton rightAlign;
    private javax.swing.JComboBox<String> sizeT;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel uName;
    private javax.swing.JButton under;
    // End of variables declaration//GEN-END:variables
}
