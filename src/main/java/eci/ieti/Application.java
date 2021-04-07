package eci.ieti;

import eci.ieti.config.AppConfiguration;
import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;

import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();

        /**
         * TodoRepository
         */
        todoRepository.deleteAll();
        System.out.println("Add some Todo elements");
        todoRepository.save(new Todo(1L, "ToDo 1", 5, "Jan 10 - 2017", "Germán", "Falta"));
        todoRepository.save(new Todo(2L, "ToDo 2", 2, "Jan 11 - 2018", "Jose", "Completado"));
        todoRepository.save(new Todo(3L, "ToDo 3", 1, "Jan 12 - 2019", "Gabriel", "Pendiente"));
        todoRepository.save(new Todo(4L, "ToDo 4", 4, "Jan 13 - 2020", "Germán", "Falta"));
        todoRepository.save(new Todo(5L, "ToDo 5", 3, "Jan 14 - 2021", "Germán", "Completado"));

        System.out.println("Paginated search of Todo elements by criteria:");
        System.out.println("-------------------------------");

        todoRepository.findByResponsible("Germán",PageRequest.of(0,2)).stream().forEach(System.out::println);

        System.out.println("Configuration part");
        System.out.println("-------------------------------");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));

        Customer customer = mongoOperation.findOne(query, Customer.class);

        /**
         * mocked data (25 Todos and 10 different users)
         */
        todoRepository.deleteAll();
        todoRepository.save(new Todo(1L, "ToDo 1", 5, "Jan 15 - 2017", "Responsible1", "Falta"));
        todoRepository.save(new Todo(2L, "ToDo 1", 5, "Jan 16 - 2017", "Responsible2", "Falta"));
        todoRepository.save(new Todo(3L, "ToDo 1", 5, "Jan 17 - 2017", "Responsible3", "Falta"));
        todoRepository.save(new Todo(4L, "ToDo 1", 5, "Jan 18 - 2017", "Responsible4", "Falta"));
        todoRepository.save(new Todo(5L, "ToDo 1", 5, "May 19 - 2017", "Responsible5", "Falta"));
        todoRepository.save(new Todo(6L, "ToDo 1", 5, "Jan 20 - 2017", "Responsible6", "Falta"));
        todoRepository.save(new Todo(7L, "ToDo 1", 5, "Jan 21 - 2017", "Responsible7", "Falta"));
        todoRepository.save(new Todo(8L, "ToDo 1", 5, "May 22 - 2017", "Responsible8", "Falta"));
        todoRepository.save(new Todo(9L, "ToDo 1", 5, "Jan 23 - 2017", "Responsible9", "Falta"));
        todoRepository.save(new Todo(10L, "ToDo 1", 5, "Jan 24 - 2017", "Responsible10", "Falta"));
        todoRepository.save(new Todo(11L, "ToDo 1", 5, "May 25 - 2017", "Responsible11", "Falta"));
        todoRepository.save(new Todo(12L, "ToDo 1", 5, "Jan 26 - 2017", "Responsible12", "Falta"));
        todoRepository.save(new Todo(13L, "ToDo 1", 5, "Jan 27 - 2017", "Responsible4", "Falta"));
        todoRepository.save(new Todo(14L, "ToDo 1", 5, "Jan 28 - 2017", "Responsible14", "Falta"));
        todoRepository.save(new Todo(15L, "ToDo 1", 5, "May 29 - 2017", "Responsible15", "Falta"));
        todoRepository.save(new Todo(16L, "ToDo 1", 5, "Jan 30 - 2017", "Responsible4", "Falta"));
        todoRepository.save(new Todo(17L, "ToDo 1", 5, "Feb 01 - 2017", "Responsible17", "Falta"));
        todoRepository.save(new Todo(18L, "ToDo 1", 5, "Jan 02 - 2017", "Responsible18", "Falta"));
        todoRepository.save(new Todo(19L, "ToDo 1", 5, "May 03 - 2017", "Responsible4", "Falta"));
        todoRepository.save(new Todo(20L, "ToDo 1", 5, "Jan 04 - 2017", "Responsible20", "Falta"));
        todoRepository.save(new Todo(21L, "ToDo 1", 5, "Jan 05 - 2017", "Responsible21", "Falta"));
        todoRepository.save(new Todo(22L, "ToDo 1", 5, "May 06 - 2017", "Responsible18", "Falta"));
        todoRepository.save(new Todo(23L, "ToDo 1", 5, "Jan 07 - 2017", "Responsible18", "Falta"));
        todoRepository.save(new Todo(24L, "ToDo 1", 5, "May 08 - 2017", "Responsible24", "Falta"));
        todoRepository.save(new Todo(25L, "ToDo 1", 5, "Jan 09 - 2017", "Responsible25", "Falta"));

        userRepository.deleteAll();
        userRepository.save(new User(1L, "Responsible18", "responsable18@mail.com"));
        userRepository.save(new User(2L, "User1", "user1@mail.com"));
        userRepository.save(new User(3L, "Responsible4", "responsable4@mail.com"));
        userRepository.save(new User(4L, "User2", "user2@mail.com"));
        userRepository.save(new User(5L, "Responsible6", "responsable6@mail.com"));
        userRepository.save(new User(6L, "User3", "user3@mail.com"));
        userRepository.save(new User(7L, "User4", "user4@mail.com"));
        userRepository.save(new User(8L, "User5", "user5@mail.com"));
        userRepository.save(new User(9L, "Responsible15", "responsable15@mail.com"));
        userRepository.save(new User(10L, "User6", "user6@mail.com"));

        /**
         * Create the following queries using the Query class:
         */
        System.out.println("Create the following queries using the Query class:");
        /**
         * Todos where the dueDate has expired
         */
        System.out.println("Todos where the dueDate has expired");
        SimpleDateFormat format = new SimpleDateFormat("MMM dd - yyyy");
        Date date = new Date();
        String actualTime = format.format(date);
        String month = actualTime.substring(0,2);
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("dueDate").not().regex("^"+month));
        List<Todo> todos = mongoOperation.find(query1, Todo.class);
        System.out.println(todos);

        /**
         * Todos that are assigned to given user and have priority greater equal to 5
         */
        System.out.println("Todos that are assigned to given user and have priority greater equal to 5");
        String resposible = "Responsible10";
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("responsible").is(resposible).and("priority").is(5));
        List<Todo> todos2 = mongoOperation.find(query2, Todo.class);
        System.out.println(todos2);

        /**
         * Users that have assigned more than 2 Todos.
         */
        System.out.println("Users that have assigned more than 2 Todos.");

        List<String> nombres = new ArrayList<String>();
        for(Todo todo: todoRepository.findAll()){
            nombres.add(todo.getResponsible());
        }
        Query query3 = new Query();
        query3.addCriteria(Criteria.where("responsible"));

        /**
         * Todos that contains a description with a length greater than 30 characters
         */
        System.out.println("Todos that contains a description with a length greater than 30 characters");
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("description").regex("[a-z,A-Z,0-9,' ']{30,}"));
        List<Todo> todos4 = mongoOperation.find(query4,Todo.class);
        System.out.println(todos4);

        /**
         * Implement the queries of the previous step using derived query methods in your repository interface
         */
        System.out.println("Implement the queries of the previous step using derived query methods in your repository interface");
        /**
         * Todos where the dueDate has expired
         */
        System.out.println("Todos where the dueDate has expired");
        List<Todo> todos5 = todoRepository.findAllByDescriptionMatchesRegex("(^"+month+")");
        System.out.println(todos5);

        /**
         * Todos that are assigned to given user and have priority greater equal to 5
         */
        System.out.println("Todos that are assigned to given user and have priority greater equal to 5");
        List<Todo> todos6 = todoRepository.findAllByResponsibleEqualsAndPriorityGreaterThanEqual("Responsible10",5);
        System.out.println(todos6);

        /**
         * Todos that contains a description with a length greater than 30 characters
         */
        System.out.println("Todos that contains a description with a length greater than 30 characters");
        List<Todo> todos7 = todoRepository.findAllByDescriptionMatchesRegex("[a-z,A-Z,0-9,' ']{30,}");
        System.out.println(todos7);

        /**
         * Delete data collections
         */
        customerRepository.deleteAll();
        productRepository.deleteAll();
        todoRepository.deleteAll();
        userRepository.deleteAll();


    }

}