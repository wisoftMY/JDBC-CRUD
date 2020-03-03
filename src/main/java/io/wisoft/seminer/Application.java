package io.wisoft.seminer;

import io.wisoft.seminer.student.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final StudentSelectService selectService = new StudentSelectService();
        final StudentInsertService insertService = new StudentInsertService();

        System.out.println("전체 학생을 조회합니다.");
        selectService.getStudentAll();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("학번을 입력해 주세요.");
        String input = scanner.next();
        selectService.getStudentByNo(input);

        System.out.println("이름을 입력해 주세요");
        input = scanner.next();
        selectService.getStudentByName(input);

        System.out.println("생일을 입력해 주세요");

        input = scanner.next();
        selectService.getStudentByBirthday(input);

          final Student student = new Student();
          final Student[] students = new Student[8];

          for(int i = 0; i< students.length; i++) {
              students[i] = new Student();
          }

        System.out.println("학번이 20191632이고, 이름이 조은엽인 학생을 추가합니다.");
        students[0].setNo("20191635");
        students[0].setName("조은엽");



        System.out.println("학번이 20191733이고, 이름이 안홍범인 학생을 추가합니다.");
        students[1].setNo("20191737");
        students[1].setName("안홍범");



        System.out.println("학번이 20191833이고, 이름이 박찬열인 학생을 추가합니다.");
        students[2].setNo("20191839");
        students[2].setName("박찬열");

        System.out.println("학번이 20191935이고, 이름이 서보민인 학생을 추가합니다.");
        students[3].setNo("20191940");
        students[3].setName("서보민");

        insertService.insertStudentMultiBatch(students);

        StudentUpdateService updateService = new StudentUpdateService();
        System.out.println("학번이 20190401인 학생의 생일을 2000-03-25로 변경합니다.");
        student.setNo("20191737");
        student.setBirthday("2000-03-25");
        updateService.updateStudentBirthday(student);

        System.out.println("학번이 20191532인 학생의 생일을 2000-03-25로 변경합니다.");
        students[0].setNo("20191532");
        students[0].setBirthday("1999-03-25");

        System.out.println("학번이 20191533인 학생의 생일을 2000-03-25로 변경합니다.");
        students[1].setNo("20191533");
        students[1].setBirthday("1999-04-25");

        System.out.println("학번이 20191534인 학생의 생일을 2000-03-25로 변경합니다.");
        students[2].setNo("20191534");
        students[2].setBirthday("1999-05-25");

        System.out.println("학번이 20191535인 학생의 생일을 2000-03-25로 변경합니다.");
        students[3].setNo("20191535");
        students[3].setBirthday("1999-06-25");

        updateService.updateStudentBirthdayMultiBatch(students);

        final StudentDeleteService studentDeleteService = new StudentDeleteService();

        String[] number = {"20191737","20191532","20191533","20191534"};


        studentDeleteService.deleteStudentMultiBatch(number);


    }
}
