package services;

import models.Term;
import repositories.TermRepository;

import java.util.List;

public class TermService implements GenericService<Term,Long>{
    private TermRepository termRepository;

    public TermService() {
        this.termRepository = new TermRepository();
    }

    @Override
    public Term save(Term term) {
        return termRepository.save(term);
    }

    @Override
    public void update(Term term) {
        termRepository.update(term);
    }

    @Override
    public void delete(Term term) {
        termRepository.delete(term);
    }

    @Override
    public void deleteById(Long id) {
        termRepository.deleteById(id);
    }

    @Override
    public Term findById(Long id) {
        return termRepository.findById(id);
    }

    @Override
    public List<Term> findAll() {
        return termRepository.findAll();
    }
}
