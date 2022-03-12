package services;

import models.Professor;
import repositories.ProfessorRepository;

import java.util.List;

public class ProfessorService implements GenericService<Professor,Long>{
    private ProfessorRepository professorRepository;

    public ProfessorService() {
        this.professorRepository = new ProfessorRepository();
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void update(Professor professor) {
        professorRepository.update(professor);
    }

    @Override
    public void delete(Professor professor) {
        professorRepository.delete(professor);
    }

    @Override
    public void deleteById(Long aLong) {

    }

 /*   @Override
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }*/

    @Override
    public Professor findById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findByUserName(String userName){
        return professorRepository.findByUserName(userName);
    }
}
