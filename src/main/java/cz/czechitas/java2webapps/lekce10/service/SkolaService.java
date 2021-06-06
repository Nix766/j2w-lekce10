package cz.czechitas.java2webapps.lekce10.service;

import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.repository.RodicRepository;
import cz.czechitas.java2webapps.lekce10.repository.StudentRepository;
import cz.czechitas.java2webapps.lekce10.repository.TridaRepository;
import cz.czechitas.java2webapps.lekce10.repository.UcitelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkolaService {

    private final RodicRepository rodicRepository;
    private final StudentRepository studentRepository;
    private final TridaRepository tridaRepository;
    private final UcitelRepository ucitelRepository;

    @Autowired
    public SkolaService(RodicRepository rodicRepository, StudentRepository studentRepository, TridaRepository tridaRepository, UcitelRepository ucitelRepository) {
        this.rodicRepository = rodicRepository;
        this.studentRepository = studentRepository;
        this.tridaRepository = tridaRepository;
        this.ucitelRepository = ucitelRepository;
    }

    public Page<Trida> seznamTrid(Pageable pageable) {
        return tridaRepository.findAll(pageable);

    }

    public Optional<Trida> detailTridy(short id) {
        return tridaRepository.findById(id);

    }

    public Optional<Student> detailStudenta(int id) {
        return studentRepository.findById(id);

    }


}