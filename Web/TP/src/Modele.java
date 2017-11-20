import java.sql.Connection;

public class Modele {

	public static void ping() {
	    private Sql2o sql2o;
		Connection con = sql2o.open();
		try {

		}
		finally {
		    con.close();
		}
	}

/*	public static Liste getItemsByCreateDate(Date createDate) {

	}*/
}