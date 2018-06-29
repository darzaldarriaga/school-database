package com.darwinsofttech.school.ui.teacher;

import com.darwinsofttech.school.repository.teacher.Teacher;
import com.darwinsofttech.school.service.teacher.TeacherService;
import com.darwinsofttech.school.ui.Reader;

import java.io.IOException;

public class TeacherUi {
    private TeacherService teacherService;

    public TeacherUi() {
    }

    public TeacherUi(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public void registerTeacher() throws IOException {
        String lastName = askForLastName();
        String firstName = askForFirstName();
        String middleName = askForMiddleName();

        teacherService.save(lastName, firstName, middleName);
    }

    public void updateTeacher() throws IOException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(askForTeacherId());
        teacher.setLastName(askForLastName());
        teacher.setFirstName(askForFirstName());
        teacher.setMiddleName(askForMiddleName());
        teacherService.update(teacher);
    }

    public String askForLastName() throws IOException {
        System.out.print("Enter last name: ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public String askForFirstName() throws IOException {
        System.out.print("Enter first name: ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public String askForMiddleName() throws IOException {
        System.out.print("Enter middle name (Enter 0 if no applicable): ");
        return Reader.INSTANCE.getReader().readLine();
    }

    public int askForTeacherId() throws IOException {
        System.out.print("Enter id number: ");
        try {
            return Integer.parseInt(Reader.INSTANCE.getReader().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id");
            return askForTeacherId();
        }
    }
}
