package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Close for resultSet and preparedStatement : to reuse for logic - controller with closing
 * @author Juyoung Choi
 *
 */

public class CloseforSQL {
	
	public void cleanup(ResultSet rs, PreparedStatement stmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}		
	}

	public void cleanup(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}		
	}
}
