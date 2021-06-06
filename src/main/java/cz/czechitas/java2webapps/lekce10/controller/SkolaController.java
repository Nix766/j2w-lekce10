package cz.czechitas.java2webapps.lekce10.controller;

import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.service.SkolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SkolaController {

    private final SkolaService service;

    @Autowired
    public SkolaController(SkolaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView seznamTrid(@PageableDefault(sort={"nazev"}) Pageable pageable) {
        return new ModelAndView("seznam")
                    .addObject("tridy", service.seznamTrid(pageable));
    }

    @GetMapping("/trida")
    public Object trida(short id) {
        Optional<Trida> trida = service.detailTridy(id);
        if (trida.isPresent()) {
            return new ModelAndView("trida")
                    .addObject("trida", trida.get());
        }
        return null;
    }

    @GetMapping("/student")
    public Object student(int id) {
        Optional<Student> student = service.detailStudenta(id);
        if (student.isPresent()) {
            return new ModelAndView("student")
                    .addObject("student", student.get());
        }
        return null;
    }

    /**
     * Získání aktuální URL s query parametry bez parametrů {@code size} a {@code page}.
     *
     * Je to ošklivé, ale dělá to, co potřebuju…
     */
    @ModelAttribute("currentURL")
    public String currentURL(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        return UriComponentsBuilder
                .newInstance()
                .path(helper.getOriginatingRequestUri(request))
                .query(helper.getOriginatingQueryString(request))
                .replaceQueryParam("size")
                .replaceQueryParam("page")
                .build()
                .toString();
    }
}
