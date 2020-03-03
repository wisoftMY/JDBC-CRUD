package io.wisoft.seminer.student;

import io.wisoft.seminer.util.PgSqlAccess;

import java.sql.*;

public class StudentUpdateService {

    public void updateStudentBirthday(final Student student) {
        final String query = "UPDATE student SET birthday = ? where no = ?";
        try (Connection conn = PgSqlAccess.setConnection(); // Connection은 rollback기능을 catch문에 넣어줘야하기 때문에 이 구분에 들어가지 않는다.
             PreparedStatement ps = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            ps.setDate(1, Date.valueOf(student.getBirthday()));
            ps.setString(2,student.getNo());
            final int result = ps.executeUpdate();
            conn.commit();
            System.out.println(result + "건이 처리되었습니다.");

        } catch (SQLException e) {

            System.out.format("SQLException: %s, SQLState: %s", e.getMessage(), e.getSQLState());

        }
    }

    public void updateStudentBirthdayMultiBatch(final Student[] students){
        final String query = "UPDATE student SET birthday = ? WHERE no = ?";
        try(Connection conn = PgSqlAccess.setConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);

            for (Student student : students){
                if (student.getBirthday() == null) {
                    ps.setNull(1, Types.DATE);
                } else {
                    ps.setDate(1, Date.valueOf(student.getBirthday()));
                }
                ps.setString(2, student.getNo());
                ps.addBatch();
                ps.clearParameters();
            }

            final int[] result = ps.executeBatch();
            conn.commit();
            System.out.println(result.length + "건이 처리 되었습니다.");
        } catch (SQLException e) {
            System.out.format("SQLException: %s, SQLState :%s", e.getMessage(), e.getSQLState());

        }

    }
}
