package com.example.springboot.demo.controller;

import com.example.springboot.demo.Mapper.TaskMapper;
import com.example.springboot.demo.bean.Task;
import com.example.springboot.demo.singleton.SingletonMybatis;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") //在类上使用RequestMapping，里面设置的value就是方法的父路径
public class Controller {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        sqlSessionFactory =  SingletonMybatis.getSqlSessionFactory();
    }
    @RequestMapping  //如果方法上的RequestMapping没有value，则此方法默认被父路径调用
    public String index(){
        return "hello spring boot";
    }

    //这里体现了restful风格的请求，按照请求的类型，来进行增删查改。
    //设计restful api（其实也就是URL），不要有冗余，例如不要写成getUsers，URL中
    //最好不要有动词。
    @RequestMapping(method = RequestMethod.GET,value = "/tasks")
    public List<Task> getUsers(){
        List<Task> listTasks;
        //获取一个连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //得到映射器
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            //调用接口中的方法去执行xml文件中的SQL语句
            listTasks = taskMapper.getTasks();
            //要提交后才会生效
            sqlSession.commit();
        }finally {
            //最后记得关闭连接
            sqlSession.close();
        }

        return listTasks;
        //return "1";
    }
    //这里用的是路径变量，就是{}括起来的，会当做变量读进来


    @RequestMapping(method = RequestMethod.GET,value = "/tasks/{taskId}")
    public Task getUser(@PathVariable int userId){
        Task task;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            task = taskMapper.getById(userId);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return task;
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/tasks/{id}")
    public boolean updateUser(@PathVariable int userid,@PathVariable String content,String createdtime){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            Task task = new Task(userid,content,createdtime);
            taskMapper.updateTask(task);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/tasks")
    public boolean deleteUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            taskMapper.deleteAllTasks();
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/tasks/{taskId}")
    public boolean deleteUser(@PathVariable int userId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            taskMapper.deleteTask(userId);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
        return true;
    }


}

