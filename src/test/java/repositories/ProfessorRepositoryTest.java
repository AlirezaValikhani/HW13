package repositories;

import models.Professor;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ProfessorRepositoryTest {
    private static SessionFactory sessionFactory;
    private static ProfessorRepository professorRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        professorRepository = new ProfessorRepository();
    }

    @Test
    void saveTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        // Act
        Professor savedProfessor = professorRepository.save(professor);
        //Assert
        assertNotNull(savedProfessor);
    }

    @Test
    void updateTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        Professor savedProfessor = professorRepository.save(professor);
        professor.setId(savedProfessor.getId());
        //Act
        Professor professor1 = new Professor(professor.getId(),"w1","w1","w1","w1","w1","w1",1d,null);
        Professor updatedProfessor = professorRepository.update(professor1);
        //Assert
        assertNotNull(updatedProfessor);
        assertEquals("w1",updatedProfessor.getUserName());
        assertEquals("w1",updatedProfessor.getPassword());
        assertEquals(professor.getId(),updatedProfessor.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        Professor savedProfessor = professorRepository.save(professor);
        professor.setId(savedProfessor.getId());
        //Act
        professorRepository.delete(professor);
        //Assert
        assertNull(professorRepository.findById(professor.getId()));
        assertEquals(0,professorRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        Professor savedProfessor = professorRepository.save(professor);
        professor.setId(savedProfessor.getId());
        //Act
        professorRepository.deleteById(professor.getId());
        //Assert
        assertNull(professorRepository.findById(professor.getId()));
        assertEquals(0,professorRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        Professor savedProfessor = professorRepository.save(professor);
        professor.setId(savedProfessor.getId());
        //Act
        professorRepository.findById(professor.getId());
        //Assert
        assertNotNull(professor);
        assertNotEquals(0,professor.getId());
        assertEquals(1,professorRepository.findAll().size());
    }

    @Test
    void findAllTest(){
        //Arrange
        Professor professor = new Professor(null,"w","w","w","w","w","w",1d,null);
        Professor savedProfessor = professorRepository.save(professor);
        professor.setId(savedProfessor.getId());
        //Act
        professorRepository.findAll();
        //Assert
        assertEquals(professor.getUserName(),professorRepository.findAll().get(0).getUserName());
        assertNotEquals(0,professorRepository.findAll());
    }

    @AfterEach
    public void cleanUp(){
        professorRepository.truncate();
    }
}