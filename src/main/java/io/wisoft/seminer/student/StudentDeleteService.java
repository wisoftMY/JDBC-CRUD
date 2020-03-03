package io.wisoft.seminer.student;

import io.wisoft.seminer.util.PgSqlAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleteService {

    public void deleteStudentNo(final String number) {
        final String query = "DELETE FROM student WHERE no = ?";
        try (Connection conn = PgSqlAccess.setConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            ps.setString(1, number);
            ps.executeUpdate();

            final int result = ps.executeUpdate();
            conn.commit();
            System.out.println(result + "건이 처리되었습니다.");

        } catch (SQLException e) {

            System.out.format("SQLException: %s, SQLState: %s", e.getMessage(), e.getSQLState());

        }

    }

    public void deleteStudentMultiBatch(final String[] numbers) {
        final String query = "DELETE FROM student WHERE no = ?";
        try (Connection conn = PgSqlAccess.setConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            for(String number : numbers) {
                ps.setString(1, number);
                ps.addBatch();
                ps.clearParameters();
            }


            final int[] result = ps.executeBatch();
            conn.commit();
            System.out.println(result.length + "건이 처리되었습니다.");

        } catch (SQLException e) {

            System.out.format("SQLException: %s, SQLState: %s", e.getMessage(), e.getSQLState());

        }
    }
}
