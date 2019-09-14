import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

import static spark.Spark.get;
import static spark.Spark.post;
public class App {
        public static void main(String[] args) {
            staticFileLocation("/public");

            get("/", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                ArrayList<Squad> squads  = (ArrayList<Squad>) Squad.all();
                model.put("squads",squads );
                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());

            //route that makes new squad
            post("/posts/new", (request, response) -> { //URL to make new post on POST route
                Map<String, Object> model = new HashMap<>();

                String name = request.queryParams("name");
                String description = request.queryParams("description");
            model.put("squad", description);
                return new ModelAndView(model, "success.hbs");
            }, new HandlebarsTemplateEngine());

            //get: show all posts
            get("/", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                List<Squad> squads = Squad.all();
                model.put("squads", squads);

                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());
        }
    }

