package repositories;

import models.Student;
import models.Term;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TermRepositoryTest {
    private static SessionFactory sessionFactory;
    private static StudentRepository studentRepository;
    private static TermRepository termRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        termRepository = new TermRepository();
    }

    @Test
    void saveTest(){
        //Arrange
        Term t = new Term(null,"2",new HashSet<>());
        //Act
        Term t1 = termRepository.save(t);
        //Assert
        assertNotNull(t1);
        assertEquals(1,t.getId());
    }

    @Test
    void updateTest(){
        //Arrange
        Term term = new Term(null,"2",new HashSet<>());
        Term t = termRepository.save(term);
        term.setId(t.getId());
        //Act
        Term term1 = new Term(term.getId(),"1",new HashSet<>());
        Term updatedTerm = termRepository.update(term1);
        //Assert
        assertNotNull(updatedTerm);
        assertEquals("1",updatedTerm.getTermNumber());
        assertEquals(term.getId(),updatedTerm.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Term term = new Term(null,"2",new HashSet<>());
        Term t = termRepository.save(term);
        term.setId(t.getId());
        //Act
        termRepository.delete(term);
        //Assert
        assertNull(termRepository.findById(term.getId()));
        assertEquals(0,termRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Term term = new Term(null,"2",new HashSet<>());
        Term t = termRepository.save(term);
        term.setId(t.getId());
        //Act
        termRepository.deleteById(term.getId());
        //Assert
        assertNull(termRepository.findById(term.getId()));
        assertEquals(0,termRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Term term = new Term(null,"2",new HashSet<>());
        Term t = termRepository.save(term);
        term.setId(t.getId());
        //Act
        termRepository.findById(term.getId());
        //Assert
        assertNotNull(term);
        assertNotEquals(0,term.getId());
        assertEquals(1,termRepository.findAll().size());
    }

    @Test
    void findAllTest(){
        //Arrange
        Term term = new Term(null,"2",new HashSet<>());
        Term t = termRepository.save(term);
        term.setId(t.getId());
        //Act
        termRepository.findAll();
        //Assert
        assertEquals(term.getTermNumber(),termRepository.findAll().get(0).getTermNumber());
        assertNotEquals(0,termRepository.findAll());
    }

    @AfterEach
    public void cleanUp(){
        termRepository.truncate();
    }

}