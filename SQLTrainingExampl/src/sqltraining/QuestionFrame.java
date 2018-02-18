/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqltraining;

import DAO.Factory;
import Logic.Answer;
import Logic.Lecture;
import Logic.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import sqltraining.MainFrame;

/**
 *
 * @author Admin
 */
public class QuestionFrame extends javax.swing.JFrame {

    /**
     * Creates new form QuestionFrame
     */
    private static List<JLabel> labels = new ArrayList<JLabel>();
    private static List<JTextField> QuestionTextField = new ArrayList<JTextField>();
    private static List<JTextField> answerTextField = new ArrayList<JTextField>();
    private static List<JRadioButton> radioButton = new ArrayList<JRadioButton>();
    private static List<JCheckBox> checkButton = new ArrayList<JCheckBox>();
    private static List<String> typeQuestion = new ArrayList<String>();
    private static List<String> newAnswer = new ArrayList<String>();
    private static List<Boolean> newStatus = new ArrayList<Boolean>();
    static List<Integer> id_Quest = new ArrayList<Integer>();
    static List<Integer> id_Answer = new ArrayList<Integer>();
    static List<Integer> id_AnswerForOne = new ArrayList<Integer>();
    ArrayList<String> answerForQ = new ArrayList<String>();
    JTextField qtf ;
    JRadioButton rb;
    JTextField jtFRadio;
    JCheckBox jcB;
    JTextField jtFCheck;
    JTextField jtFOpen;
    int idLection = 0;
    int questSize = 0;
    final JScrollPane scrollPane;
     final Font fontText;
     final Font font;
   // String typeQ = "openanswer";
    public QuestionFrame(Integer idLect) {
        initComponents();
        idLection = idLect;
        typeQuestion.clear();
        JPanel topPanel = new JPanel();
        FlowLayout experimentLayout = new FlowLayout(FlowLayout.RIGHT);
        FlowLayout centR = new FlowLayout(FlowLayout.CENTER);
        //topPanel.setLayout(experimentLayout);
        
        
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        textPanel.setBackground(new java.awt.Color(248, 148, 6));
        buttonPanel.setBackground(new java.awt.Color(248, 148, 6));
        buttonPanel.setLayout(experimentLayout);
        JButton closeButton = new JButton("X");
        JButton hideButton = new JButton("_");
        JLabel nameFrame = new JLabel();
        closeButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        hideButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        nameFrame.setText("Questions And Answer for Lection "+idLect);
        nameFrame.setFont(new java.awt.Font("Tahoma", 1, 24));
        nameFrame.setForeground(Color.WHITE);

        textPanel.setLayout(centR);
        closeButton.setBackground(new java.awt.Color(248, 148, 6));
        closeButton.setBorderPainted(false);
        closeButton.setForeground(Color.WHITE);
        hideButton.setForeground(Color.WHITE);
        hideButton.setBorderPainted(false);
        hideButton.setBackground(new java.awt.Color(248, 148, 6));
        topPanel.setBackground(new java.awt.Color(248, 148, 6));
        topPanel.setPreferredSize(new java.awt.Dimension(180, 80));
       
        
        fontText = new Font("Verdana", Font.PLAIN, 18);
        font = new Font("Tahoma", 1, 18);
        closeButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        hideButton.setFont(new java.awt.Font("Tahoma", 1, 24));
        closeButton.setFocusable(false);
        hideButton.setFocusable(false);
        textPanel.add(nameFrame);
        buttonPanel.add(hideButton);
        buttonPanel.add(closeButton);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(textPanel,BorderLayout.LINE_START);
        topPanel.add(buttonPanel, BorderLayout.LINE_END);
        JPanel butPanel = new JPanel();   
        butPanel.setBackground(new java.awt.Color(44, 62, 80));
        JButton backButton = new JButton("<Back");
        backButton.setBackground(new java.awt.Color(242,38,19));
        backButton.setFont(font);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        butPanel.add(backButton);
         
        
        JButton RadioButton = new JButton("One Answer");
        RadioButton.setBackground(new java.awt.Color(34,167,240));
        RadioButton.setFont(font);
        RadioButton.setForeground(Color.WHITE);
        RadioButton.setFocusable(false);
        butPanel.add(RadioButton);
        
        JButton CheckButton = new JButton("Many Answer");
        CheckButton.setBackground(new java.awt.Color(34,167,240));
        CheckButton.setFont(font);
        CheckButton.setForeground(Color.WHITE);
        CheckButton.setFocusable(false);
        butPanel.add(CheckButton);
        
        JButton openQuest = new JButton("Open Answer");
        openQuest.setBackground(new java.awt.Color(34,167,240));
        openQuest.setFont(font);
        openQuest.setForeground(Color.WHITE);
        openQuest.setFocusable(false);
        butPanel.add(openQuest);
        
        JButton getInfo = new JButton("Save");
        getInfo.setBackground(new java.awt.Color(34,167,240));
        getInfo.setForeground(Color.WHITE);
        getInfo.setFont(font);
        getInfo.setFocusable(false);
        butPanel.add(getInfo);
//        final JPanel MainPanel = new JPanel();
        scrollPane = new JScrollPane(MainPanel);
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
        
        setDoneQuestions();
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mM = new MainMenu();
                mM.setVisible(true);
            }           
        });
         
        getInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if(labels.size() > 0) {
//                    int index = labels.size() - 1;
//                    JLabel label = labels.remove(index);
//                    jPanel1.remove(label);
//                    jPanel1.repaint();
//                    scrollPane.revalidate();                    
//                }
               int res = 0;
               int rRes = 0;
               int cRes = 0;
               for(int i =0;i<questSize;i++)
               {   Question quest = new Question();
                   quest.setIdQuestions(id_Quest.get(i));
                   quest.setQuestions(QuestionTextField.get(i).getText());
                   Factory.getInstance().getQuestionDAO().updateQuestion(quest);
                   //System.out.println("Type: "+typeQuestion.get(i));
                   System.out.println(QuestionTextField.get(i).getText());
                   
                   switch(typeQuestion.get(i))
                   {
                       case "oneanswer":
                                        newAnswer.clear();
                                        newStatus.clear();
                                        id_AnswerForOne.clear();
                                        for(int s = res; s <(res+4);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());   
                                            id_AnswerForOne.add(id_Answer.get(s));
                                        }
                                        
                                        
                                        for(int r = rRes; r<(rRes+4);r++)
                                        {
                                            newStatus.add(radioButton.get(r).isSelected());
                                        }
                                        rRes = rRes+4;
                                        
                                        for(int me = 0; me<newAnswer.size();me++)
                                        {   
                                            
                                            Answer answs = new Answer();
                                            answs.setIdAnswer(id_AnswerForOne.get(me));
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(newStatus.get(me));
                                            Factory.getInstance().getAnswerDAO().update(answs);
                                            //System.out.println(newAnswer.get(me)+" - "+newStatus.get(me));
                                        }
                                        
                                        res=res+4;
                                        
                           continue;
                       case "manyanswer":
                                        newAnswer.clear();
                                        newStatus.clear();
                                        id_AnswerForOne.clear();
                                        for(int s = res; s <(res+4);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());
                                            id_AnswerForOne.add(id_Answer.get(s));
                                        }
                                        
                                        
                                        for(int c = cRes; c<(cRes+4);c++)
                                        {
                                            newStatus.add(checkButton.get(c).isSelected());
                                            //System.out.println(checkButton.get(c).isSelected());  
                                        }
                                        cRes = cRes+4;
                                        for(int me =0; me<newAnswer.size();me++)
                                        {
                                            Answer answs = new Answer();
                                            answs.setIdAnswer(id_AnswerForOne.get(me));
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(newStatus.get(me));
                                            Factory.getInstance().getAnswerDAO().update(answs);
                                           // System.out.println(newAnswer.get(me)+" - "+newStatus.get(me));
                                        }
                                        res=res+4;
                                         // System.out.println(res);
                           continue;
                       case "openanswer":
                                        newAnswer.clear();
                                        id_AnswerForOne.clear();
                                        //newStatus.clear();
                                        for(int s = res; s <(res+1);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());
                                            id_AnswerForOne.add(id_Answer.get(s));
                                            // System.out.println("Open: - "+answerTextField.get(s).getText());
                                        }
                                        
                                         for(int me =0; me<newAnswer.size();me++)
                                        {
                                            Answer answs = new Answer();
                                            answs.setIdAnswer(id_AnswerForOne.get(me));
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(newStatus.get(me));
                                            Factory.getInstance().getAnswerDAO().update(answs);
                                           // System.out.println(newAnswer.get(me));
                                        }
                                         res=res+1;
                           continue;                                      
                       default:
                           break;
                           
                   }
                  
               }
               for(int i =questSize;i<typeQuestion.size();i++)
               {
                   Lecture lect = new Lecture();
                   lect.setIdLecture(idLection);
                   Question quest = new Question();
                   quest.setQuestions(QuestionTextField.get(i).getText());
                   quest.setType(typeQuestion.get(i));
                   quest.setLecture(lect);
                   
                   Factory.getInstance().getQuestionDAO().Add(quest);
                   System.out.println("New: "+QuestionTextField.get(i).getText());
                   switch(typeQuestion.get(i))
                   {
                       case "oneanswer":
                                        newAnswer.clear();
                                        newStatus.clear();
                                        for(int s = res; s <(res+4);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());                       
                                        }
                                        res=res+4;
                                        
                                        for(int r = rRes; r<(rRes+4);r++)
                                        {
                                            newStatus.add(radioButton.get(r).isSelected());
                                        }
                                        rRes = rRes+4;
                                        
                                        for(int me =0; me<newAnswer.size();me++)
                                        {
                                            Answer answs = new Answer();
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(newStatus.get(me));
                                            answs.setQuestion(quest);
                                            System.out.println("New: "+quest.getIdQuestions());
                                            Factory.getInstance().getAnswerDAO().Add(answs);
                                        }
                                        
                           continue;
                       case "manyanswer":
                                        newAnswer.clear();
                                        newStatus.clear();
                                        for(int s = res; s <(res+4);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());
                                        }
                                        res=res+4;
                                        
                                        for(int c = cRes; c<(cRes+4);c++)
                                        {
                                            newStatus.add(checkButton.get(c).isSelected());
                                            //System.out.println(checkButton.get(c).isSelected());  
                                        }
                                        cRes = cRes+4;
                                         for(int me =0; me<newAnswer.size();me++)
                                        {
                                            Answer answs = new Answer();
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(newStatus.get(me));
                                            answs.setQuestion(quest);
                                            Factory.getInstance().getAnswerDAO().Add(answs);
                                        }
                           continue;
                       case "openanswer":
                                        newAnswer.clear();
                                        //newStatus.clear();
                                        for(int s = res; s <(res+1);s++)
                                        {
                                            newAnswer.add(answerTextField.get(s).getText());
                                            // System.out.println("Open: - "+answerTextField.get(s).getText());
                                        }
                                        res=res+1;
                                         for(int me =0; me<newAnswer.size();me++)
                                        {
                                            Answer answs = new Answer();
                                            answs.setAnswer(newAnswer.get(me));
                                            answs.setCorrect(true);
                                            answs.setQuestion(quest);
                                            Factory.getInstance().getAnswerDAO().Add(answs);
                                        }
                                        
                                        
                           continue;                                      
                       default:
                           break;
                   }
               }
            }           
        });
        
        RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = labels.size() + 1;
                JLabel label = new JLabel("Question № " + number);
                label.setForeground(Color.WHITE);
                labels.add(label);
                label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                label.setFont(font);
                MainPanel.add(label);
                
                qtf = new JTextField("Question nomber "+number+"");
                qtf.setBackground(new java.awt.Color(108, 122, 137));
                qtf.setForeground(new java.awt.Color(228, 241, 254));
                qtf.setFont(fontText);
                QuestionTextField.add(qtf);
                qtf.setPreferredSize(new Dimension(50,50));
                qtf.setAlignmentX(JTextField.LEFT_ALIGNMENT);
//                qtf.setFont(font);
                MainPanel.add(qtf);
                                    JPanel RadioPanel = new JPanel();
                                    RadioPanel.setBackground(new java.awt.Color(44, 62, 80));
                                    ButtonGroup group = new ButtonGroup();
                                    RadioPanel.setLayout(new GridBagLayout());
                                    GridBagConstraints rGrid = new GridBagConstraints();
                                    RadioPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                    for(int i=0;i<4;i++)
                                    {
                                        
                                        rb = new JRadioButton();
                                        
                                        rb.setBackground(new java.awt.Color(44, 62, 80));
                                        jtFRadio = new JTextField();
                                        jtFRadio.setPreferredSize(new Dimension(400,30));
                                        jtFRadio.setBackground(new java.awt.Color(108, 122, 137));
                                        jtFRadio.setForeground(new java.awt.Color(228, 241, 254));
                                        jtFRadio.setFont(new java.awt.Font("Tahoma", 0, 14));
                                        radioButton.add(rb);
                                        
                                        group.add(rb);
                                        answerTextField.add(jtFRadio);
                                        jtFRadio.setAlignmentX(JTextField.LEFT_ALIGNMENT);
                                        rb.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
                                        rGrid.fill = GridBagConstraints.HORIZONTAL;
                                        rGrid.gridx = 0;
                                        rGrid.gridy = i;
                                        RadioPanel.add(rb,rGrid);
                                        rGrid.fill = GridBagConstraints.HORIZONTAL;
                                        rGrid.gridx = 1;
                                        rGrid.gridy = i;
                                        RadioPanel.add(jtFRadio,rGrid);
                                        MainPanel.add(RadioPanel);
                                        
                                    }
                    typeQuestion.add("oneanswer");                
                    scrollPane.revalidate();
            }           
        });
        
        CheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = labels.size() + 1;
                JLabel label = new JLabel("Question № " + number);
                label.setForeground(Color.WHITE);
                labels.add(label);
                label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                label.setFont(font);
                MainPanel.add(label);
                
                qtf = new JTextField("Question nomber "+number+" ");
                qtf.setBackground(new java.awt.Color(108, 122, 137));
                qtf.setForeground(new java.awt.Color(228, 241, 254));
                qtf.setFont(fontText);
                QuestionTextField.add(qtf);
                qtf.setPreferredSize(new Dimension(50,50));
                qtf.setAlignmentX(JTextField.LEFT_ALIGNMENT);
//                qtf.setFont(font);
                MainPanel.add(qtf);
                        
                                    JPanel CheckPanel = new JPanel();
                                    CheckPanel.setBackground(new java.awt.Color(44, 62, 80));
                                    CheckPanel.setLayout(new GridBagLayout());
                                    GridBagConstraints cGrid = new GridBagConstraints();
                                    CheckPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                    
                                    for(int i=0;i<4;i++)
                                    {
                                        
                                        jcB = new JCheckBox();
                                        jcB.setBackground(new java.awt.Color(44, 62, 80));
                                        jtFCheck = new JTextField();
                                        jtFCheck.setPreferredSize(new Dimension(400,30));
                                        jtFCheck.setBackground(new java.awt.Color(108, 122, 137));
                                        jtFCheck.setForeground(new java.awt.Color(228, 241, 254));
                                        jtFCheck.setFont(new java.awt.Font("Tahoma", 0, 14));
                                        checkButton.add(jcB);
                                        answerTextField.add(jtFCheck);
                                        jtFCheck.setAlignmentX(JTextField.LEFT_ALIGNMENT);
                                        jcB.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
                                        cGrid.fill = GridBagConstraints.HORIZONTAL;
                                        cGrid.gridx = 0;
                                        cGrid.gridy = i;
                                        CheckPanel.add(jcB,cGrid);
                                        cGrid.fill = GridBagConstraints.HORIZONTAL;
                                        cGrid.gridx = 1;
                                        cGrid.gridy = i;
                                        CheckPanel.add(jtFCheck,cGrid);
                                        MainPanel.add(CheckPanel);
                                        
                                    }
                                    typeQuestion.add("manyanswer");
                                    scrollPane.revalidate();
            }           
        });
        
        openQuest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = labels.size() + 1;
                JLabel label = new JLabel("Question № " + number);
                label.setForeground(Color.WHITE);
                labels.add(label);
                label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                label.setFont(font);
                MainPanel.add(label);
                
                qtf = new JTextField("Question nomber "+number);
                qtf.setBackground(new java.awt.Color(108, 122, 137));
                qtf.setForeground(new java.awt.Color(228, 241, 254));
                qtf.setFont(fontText);
                QuestionTextField.add(qtf);
                qtf.setPreferredSize(new Dimension(50,50));
                qtf.setAlignmentX(JTextField.LEFT_ALIGNMENT);
//                qtf.setFont(font);
                MainPanel.add(qtf);
                
                                    JPanel TextPanel = new JPanel();
                                    TextPanel.setBackground(new java.awt.Color(44, 62, 80));
                                    TextPanel.setLayout(new GridBagLayout());
                                    GridBagConstraints tGrid = new GridBagConstraints();
                                    TextPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                    
                                    
                                    jtFOpen = new JTextField();
                                    jtFOpen.setPreferredSize(new Dimension(400,30));
                                    jtFOpen.setBackground(new java.awt.Color(108, 122, 137));
                                    jtFOpen.setForeground(new java.awt.Color(228, 241, 254));
                                    jtFOpen.setFont(new java.awt.Font("Tahoma", 0, 14));
                                    answerTextField.add(jtFOpen);
                                    tGrid.fill = GridBagConstraints.HORIZONTAL;
                                    tGrid.gridx = 0;
                                    tGrid.gridy = 0;
                                    TextPanel.add(jtFOpen,tGrid);
                                    MainPanel.add(TextPanel);
                                    
                                    typeQuestion.add("openanswer");
                                    scrollPane.revalidate();
            }           
        });
        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }           
        });
        
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }           
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(butPanel, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
         
        setPreferredSize(new Dimension(900, 700));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    
    public void setDoneQuestions()
    {
        typeQuestion.clear();
        labels.clear();
        id_Quest.clear();
        QuestionTextField.clear();
        id_Answer.clear();
        answerTextField.clear();
        checkButton.clear();
        radioButton.clear();
        Collection listQues = Factory.getInstance().getQuestionDAO().getQuestionForID(idLection);
        Iterator itQ = listQues.iterator();
          while(itQ.hasNext())
          {     
                int number = labels.size() + 1;
                JLabel label = new JLabel("Question № " + number);
                label.setForeground(Color.WHITE);
                labels.add(label);
                label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                label.setFont(font);
                MainPanel.add(label);
                Question question = (Question) itQ.next();
                
                typeQuestion.add(question.getType());
                questSize = typeQuestion.size();
                //System.out.println(typeQuestion.toString());
                id_Quest.add(question.getIdQuestions());
                qtf = new JTextField(question.getQuestions());
                qtf.setBackground(new java.awt.Color(108, 122, 137));
                qtf.setForeground(new java.awt.Color(228, 241, 254));
                qtf.setFont(fontText);
                QuestionTextField.add(qtf);
                qtf.setPreferredSize(new Dimension(50,50));
                qtf.setAlignmentX(JTextField.LEFT_ALIGNMENT);
                MainPanel.add(qtf);
                ButtonGroup group = new ButtonGroup();
                System.out.println(question.getQuestions());
                Collection answers = Factory.getInstance().getAnswerDAO().getAnswerForIDQ(question.getIdQuestions());
                Iterator it = answers.iterator();
                  while(it.hasNext())
                  {
                          Answer answ = (Answer) it.next();
                          id_Answer.add(answ.getIdAnswer());
                          switch(question.getType())
                            {
                                case "oneanswer":   
                                                    System.out.println(answ.getIdAnswer()+" "+answ.getAnswer());  
                                                    JPanel RadioPanel = new JPanel();
                                                    RadioPanel.setBackground(new java.awt.Color(44, 62, 80));
                                                    
                                                    RadioPanel.setLayout(new GridBagLayout());
                                                    GridBagConstraints rGrid = new GridBagConstraints();
                                                    RadioPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                                    /*************************One Answer*********************************/
                                                        rb = new JRadioButton();
                                                        rb.setSelected(answ.isCorrect()); 
                                                        rb.setBackground(new java.awt.Color(44, 62, 80));
                                                        radioButton.add(rb);
                                                        group.add(rb);
                                                        
                                                        jtFRadio = new JTextField();
                                                        jtFRadio.setPreferredSize(new Dimension(400,30));
                                                        jtFRadio.setBackground(new java.awt.Color(108, 122, 137));
                                                        jtFRadio.setForeground(new java.awt.Color(228, 241, 254));
                                                        jtFRadio.setFont(new java.awt.Font("Tahoma", 0, 14));
                                                        
                                                        
                                                        jtFRadio.setText(answ.getAnswer());
                                                        
                                                        answerTextField.add(jtFRadio);
                                                        jtFRadio.setAlignmentX(JTextField.LEFT_ALIGNMENT);
                                                        rb.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
                                                        rGrid.fill = GridBagConstraints.HORIZONTAL;
                                                        rGrid.gridx = 0;
                                                        rGrid.gridy = 0;
                                                        RadioPanel.add(rb,rGrid);
                                                        rGrid.fill = GridBagConstraints.HORIZONTAL;
                                                        rGrid.gridx = 1;
                                                        rGrid.gridy = 0;
                                                        RadioPanel.add(jtFRadio,rGrid);
                                                        MainPanel.add(RadioPanel);                                                
                                                    scrollPane.revalidate();
                                                    
//                                                 System.out.println();   


                                    continue;
                                case "manyanswer":
                                                    JPanel CheckPanel = new JPanel();
                                                    CheckPanel.setBackground(new java.awt.Color(44, 62, 80));
                                                    CheckPanel.setLayout(new GridBagLayout());
                                                    GridBagConstraints cGrid = new GridBagConstraints();
                                                    CheckPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                                    /*****************************Many Answer***************************/
                                                        jcB = new JCheckBox();
                                                        jcB.setBackground(new java.awt.Color(44, 62, 80));
                                                        jtFCheck = new JTextField();
                                                        jtFCheck.setPreferredSize(new Dimension(400,30));
                                                        jtFCheck.setBackground(new java.awt.Color(108, 122, 137));
                                                        jtFCheck.setForeground(new java.awt.Color(228, 241, 254));
                                                        jtFCheck.setFont(new java.awt.Font("Tahoma", 0, 14));
                                                        checkButton.add(jcB);
                                                        answerTextField.add(jtFCheck);
                                                        jtFCheck.setText(answ.getAnswer());
                                                        jcB.setSelected(answ.isCorrect());
                                                        jtFCheck.setAlignmentX(JTextField.LEFT_ALIGNMENT);
                                                        jcB.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
                                                        cGrid.fill = GridBagConstraints.HORIZONTAL;
                                                        cGrid.gridx = 0;
                                                        cGrid.gridy = 0;
                                                        CheckPanel.add(jcB,cGrid);
                                                        cGrid.fill = GridBagConstraints.HORIZONTAL;
                                                        cGrid.gridx = 1;
                                                        cGrid.gridy = 0;
                                                        CheckPanel.add(jtFCheck,cGrid);
                                                        MainPanel.add(CheckPanel);

                                                    scrollPane.revalidate();
                                    continue;
                                case "openanswer":
                                                JPanel TextPanel = new JPanel();
                                                TextPanel.setBackground(new java.awt.Color(44, 62, 80));
                                                TextPanel.setLayout(new GridBagLayout());
                                                GridBagConstraints tGrid = new GridBagConstraints();
                                                TextPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                                                
                                                /***************Open Answer********************************/
                                                jtFOpen = new JTextField();
                                                jtFOpen.setPreferredSize(new Dimension(400,30));
                                                jtFOpen.setBackground(new java.awt.Color(108, 122, 137));
                                                jtFOpen.setForeground(new java.awt.Color(228, 241, 254));
                                                jtFOpen.setFont(new java.awt.Font("Tahoma", 0, 14));
                                                jtFOpen.setText(answ.getAnswer());
                                                answerTextField.add(jtFOpen);
                                                tGrid.fill = GridBagConstraints.HORIZONTAL;
                                                tGrid.gridx = 0;
                                                tGrid.gridy = 0;
                                                TextPanel.add(jtFOpen,tGrid);
                                                MainPanel.add(TextPanel);
                                                
                                            scrollPane.revalidate();
                                    continue;
                                default:
                                    break;
                                }  
                    }
          }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(44, 62, 80));
        setUndecorated(true);
        setResizable(false);

        MainPanel.setBackground(new java.awt.Color(44, 62, 80));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 876, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
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
            java.util.logging.Logger.getLogger(QuestionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuestionFrame(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    // End of variables declaration//GEN-END:variables
}
