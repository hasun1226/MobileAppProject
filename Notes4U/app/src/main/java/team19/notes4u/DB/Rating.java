package team19.notes4u.DB;

/**
 * Created by SUNNY on 2016-11-14.
 */

public class Rating extends Object{
    int user_id;
    int rating;

    public Rating(int user_id, int rating) {
        this.user_id = user_id;
        this.rating = rating;
    }
}
