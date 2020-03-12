package DATABASEhw2;
//

import com.sun.javafx.binding.StringFormatter;

import java.sql.*;

public class query {
    public static String stillThere(String state) {
        return "select business_id,concat(address,city,state,postal_code) as full_address, stars \n" +
                "from business \n" +
                "where is_open =1 and state ='" + state + "' \n" +
                "order by review_count desc limit 10;\n";
    }

    public static  String topReviews(String biz_id) {
//       return  "select users.user_id, users.name, " +
//                "review.user_id, review.stars, review.text " +
//                "from users " +
//                "INNER JOIN review " +
//                "ON review.user_id = \"users\".user_id where business_id = \'" +biz_id +
////                "\' order by review.stars desc limit 5;";
//        return " select user_id, name , stars, text \n" +
//                "from(select * from review natural join users \n " +
//                " where business_id = '" + biz_id + "'\n"
//                + "order by useful desc limit 5) as mf \n";
        return "select u.user_id, u.name, r.stars \n" +
                "from review r inner join users u  on business_id='" + biz_id + "' \n"+
                "order by r.useful desc  limit 5\n";

    }
    public static String averageRating(String uid){
    return "select user_id,name as name_of_user, average_stars as average_star_ratings \n" +
         "from users \n"+
        "where user_id='" + uid +"'\n";
    }
    public static String topBusinessInCity(String city, int eliteCount, int topCount){
        return "select a.business_id, b.name, b.review_count, stars, countt\n"+
                "                   from (select cit.business_id, count(*) countt\n"+
                "                  from (select business_id, review.user_id\n" +
                "                  from review\n"+
                "                       inner join elite_users answer\n"+
                "                         on answer.user_id = review.user_id and answer.elite = review.year) as el\n"+
                "                         inner join (select * from business\n"+
                "                         where city = '" + city + "') as cit\n"+
                "                        on cit.business_id = el.business_id\n"+
                "                         group by cit.business_id\n"+
                "                        having count(*) > " + eliteCount + "\n" +
                "                         order by count(*) desc\n"+
                "                         LIMIT " + topCount+ " ) as a\n"+
                "                        INNER JOIN (select * from business where city = '" + city+ "') as b on b.business_id = a.business_id\n";
    }
    //select  user_id, name as name_of_user, average_stars as average_star_ratings from users where user_id = 'SgYDjNCecPidsRB_su5-tw';
////    public void topBusinessInCity(){
////
////    }
//    public static void main(String[]args){
//        stillThere();
//    }
}