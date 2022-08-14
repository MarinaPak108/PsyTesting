package com.psytest.service;

import com.psytest.entity.TeacherEntity;
import com.psytest.entity.UserEntity;
import com.psytest.repo.TeacherRepository;
import com.psytest.repo.UserRepository;
import com.psytest.web.enums.UserType;
import com.psytest.web.form.TeacherCreationParameters;
import com.psytest.web.form.UserCreationParameters;
import com.psytest.web.form.UserLoginParameters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.time.LocalDate.now;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository repository;
    private final TeacherRepository tRepository;

    public UserService(UserRepository repository, TeacherRepository tRepository) {
        this.repository = repository;
        this.tRepository = tRepository;
    }

    public UserEntity createUser(UserCreationParameters parameters) {
        logger.info(String.format("creating user with parameters: %s and %s", parameters.getLogin(), parameters.getPassword()));
        UserEntity user = new UserEntity(parameters.getName(), parameters.getSurname(),
                parameters.getFathersName(), parameters.getLogin(),
                parameters.getPassword(),parameters.getBirthday(),
                parameters.getNationality(),parameters.getSex().substring(1));
        logger.info("Save to repository user");
        return repository.save(user);
    }

    public void saveTeacher (TeacherEntity teacher)
    {
       tRepository.save(teacher);
    }

    public void saveUser (UserEntity user)
    {
        repository.save(user);
    }

    public void deleteTeacherById (Long id) {
        tRepository.deleteById(id);
    }

    public void deleteUserById(Long id) {repository.deleteById(id);}


    public TeacherEntity createTeacher(TeacherCreationParameters parameters) {
        logger.info(String.format("creating teacher with parameters: %s and %s", parameters.getLogin(), parameters.getPassword()));
        TeacherEntity teacher = new TeacherEntity(parameters.getName(), parameters.getSurname(),
                parameters.getFathersName(), parameters.getLogin(),
                parameters.getPassword(),parameters.getCurator());
        logger.info("Save to repository teacher");
        return tRepository.save(teacher);
    }

    public UserEntity getUser(String name) {
        UserEntity user = repository.getUserByLogin(name).get();
        logger.info("found user with id: " + user.getId());
        return user;
    }

    public TeacherEntity getTeacherById (Long id){
        return tRepository.getById(id);
    }

    public String getTeacherByUser (String name) {
        UserEntity user = repository.getUserByLogin(name).get();
        if (user.getTeacher() == null) {
            return ("Professor to be assigned");
        } else {
            return user.getTeacher().getFathersName();
        }
    }

    public String checkUser(UserLoginParameters parameters) {
        String result = UserType.UNDEFINED.getShortName();
        String login = parameters.getLogin();
        String password = parameters.getPassword();
        if (repository.findByLoginAndPassword(login, password).isPresent()) {
            result =  UserType.USER.getShortName();
        }
        if (tRepository.getTeacherEntityByLogin(login).isPresent()) {
            result = UserType.TEACHER.getShortName();
        }
        return result;
    }

    public boolean isUserExist(String login) {
        return repository.getUserByLogin(login).isPresent();
    }


    public boolean isTeacherExist(String login) {
        return tRepository.getTeacherEntityByLogin(login).isPresent();
    }

    public List<UserEntity> getUsersWithoutTeachers() {
        return repository.getUserByTeacherNull();
    }

    public UserEntity getUserById (Long id){
        return repository.getUserById(id) ;
    }


    public void assignTeacher(Long user, Long teacher) {
        UserEntity userFind = repository.getUserById(user);
        TeacherEntity teacherFind = tRepository.getById(teacher);
        List<UserEntity> usersList = teacherFind.getUsers();
        usersList.add(userFind);
        teacherFind.setUsers(usersList);
        tRepository.save(teacherFind);
        logger.info("Teacher- " + teacherFind.getFathersName() + "is assigned to user - " + userFind.getFathersName());
    }

    public TeacherEntity getTeacher(String login) {
        Optional<TeacherEntity> teacherEntityByLogin = tRepository.getTeacherEntityByLogin(login);
        if(teacherEntityByLogin.isEmpty()) {
            throw new RuntimeException("Teacher not exists");
        }
        return teacherEntityByLogin.get();
    }


    public int getUserAge(String name) {
        Optional<UserEntity> userByLogin = repository.getUserByLogin(name);
        if (userByLogin.isEmpty()) {
            throw new RuntimeException("User no found");
        }
        UserEntity user = userByLogin.get();
        LocalDate bidrthday = user.getBirthday().toLocalDate();
        return Period.between(bidrthday, now()).getYears();
    }

    public List<TeacherEntity> getTeachers() {
        return tRepository.findAll();
    }

    public List<UserEntity> getUsersWithTeachers() {
        return repository.getUserByTeacherNotNullOrderById();
    }

    public List<TeacherEntity> getCurator(){
        return  tRepository.getTeacherEntityByIsCuratorIsTrueOrderById();
    }

    public  List<TeacherEntity> getNotCurator(){
        return tRepository.getTeacherEntityByIsCuratorIsFalseOrderById();
    }
}
