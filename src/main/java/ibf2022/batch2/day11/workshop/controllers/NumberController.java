package ibf2022.batch2.day11.workshop.controllers;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path={"/", "/index"})
public class NumberController {

    Logger logger = Logger.getLogger(NumberController.class.getName());
    List<Integer> listNumber;

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @PostMapping(path="results")
    public String getResults(@RequestParam(name="number") Integer number, Model model) {

        listNumber = new LinkedList<>();
        Random rand = new SecureRandom();
        Integer imgIndex = 0;
        Integer counter = 0;

        while (counter < number) {
            imgIndex = rand.nextInt(31);
            if (!listNumber.contains(imgIndex)) {
                logger.log(Level.INFO, "imgIndex = %d".formatted(imgIndex));
                listNumber.add(imgIndex);
                counter++;
            }
        }

        System.out.printf("List Size: %d".formatted(listNumber.size()));

        model.addAttribute("list", listNumber);
        model.addAttribute("number", number);

        return "results";
    }
}
