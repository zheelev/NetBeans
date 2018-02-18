package logic;
// Generated 03.06.2017 18:49:44 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name="review"
    ,catalog="bustickets"
)
public class Review  implements java.io.Serializable {


     private Integer idReview;
     private Route route;
     private String review;
     private int grade;
     private String documentUsers;

    public Review() {
    }

    public Review(Route route, String review, int grade, String documentUsers) {
       this.route = route;
       this.review = review;
       this.grade = grade;
       this.documentUsers = documentUsers;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_review", unique=true, nullable=false)
    public Integer getIdReview() {
        return this.idReview;
    }
    
    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_route", nullable=false)
    public Route getRoute() {
        return this.route;
    }
    
    public void setRoute(Route route) {
        this.route = route;
    }

    
    @Column(name="review", nullable=false, length=150)
    public String getReview() {
        return this.review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }

    
    @Column(name="grade", nullable=false)
    public int getGrade() {
        return this.grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }

    
    @Column(name="documentUsers", nullable=false, length=30)
    public String getDocumentUsers() {
        return this.documentUsers;
    }
    
    public void setDocumentUsers(String documentUsers) {
        this.documentUsers = documentUsers;
    }
}


