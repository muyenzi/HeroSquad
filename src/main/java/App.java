import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Hero;
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
                return new ModelAndView(model, "welcome.hbs");
            }, new HandlebarsTemplateEngine());


            //get: show new post form
            get("/new", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return new ModelAndView(model, "form.hbs");
            }, new HandlebarsTemplateEngine());

            get("/posts/:id", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                int idOfPostToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
                Squad foundPost = Squad.find(idOfPostToFind); //use it to find post
                model.put("squad", foundPost); //add it to model for template to display
                return new ModelAndView(model, "squad-detail.hbs"); //individual post page.
            }, new HandlebarsTemplateEngine());


//            //route that makes new squad
            get("/created-squad", (request, response) -> { //URL to make new post on POST route
                Map<String, Object> model = new HashMap<>();
                String squadName = request.queryParams("squadName");
                String description = request.queryParams("description");
                int maxsize=Integer.parseInt(request.queryParams("size"));
                Squad newSquad = new Squad(squadName, description,maxsize);
                model.put("squad", newSquad);
                return new ModelAndView(model, "created-squad.hbs");
            }, new HandlebarsTemplateEngine());

            post("/created-squad", (request, response) -> { //URL to make new post on POST route
                Map<String, Object> model = new HashMap<>();
                String squadName = request.queryParams("squadName");
                String description = request.queryParams("description");
                int size=Integer.parseInt(request.queryParams("size"));
                Squad newSquad = new Squad(squadName, description,size);
                model.put("squads",newSquad);
                model.put("name",newSquad.getName());
                model.put("description",newSquad.getDescription());
                model.put("size",newSquad.getmNumber());
                System.out.println(newSquad.getmNumber());
                return new ModelAndView(model, "created-squad.hbs");
            }, new HandlebarsTemplateEngine());

//            //get: show all squads
            get("/squads", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                List<Squad> squads = Squad.all();
                model.put("squads", squads);

                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());


            //get: show new hero form
            get("/new-hero", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return new ModelAndView(model, "hero-form.hbs");
            }, new HandlebarsTemplateEngine());

            //route that makes new hero
            get("/created-hero", (request, response) -> { //URL to make new post on POST route
                Map<String, Object> model = new HashMap<>();
                String name = request.queryParams("heroName");
                int age=Integer.parseInt(request.queryParams("ages"));
                String strength = request.queryParams("power");
                String weakness = request.queryParams("weak");
                Hero newHero = new Hero(name, age, strength, weakness);
                model.put("heroes", newHero);

                return new ModelAndView(model, "created-hero.hbs");
            }, new HandlebarsTemplateEngine());

            post("/created-hero", (request, response) -> { //URL to make new post on POST route
                Map<String, Object> model = new HashMap<>();

                String name = request.queryParams("heroName");
                int age =Integer.parseInt (request.queryParams("ages"));
                String strength = request.queryParams("power");
                String weakness = request.queryParams("weak");
                Hero newHero = new Hero(name, age, strength, weakness);
                model.put("heroes", newHero);
                model.put("heroName",newHero.getName());
                model.put("ages",newHero.getAge());
                model.put("power",newHero.getStrength());
                model.put("weak",newHero.getWeakness());
                System.out.println(newHero.getName());
                System.out.println(newHero.getAge());
                return new ModelAndView(model, "created-hero.hbs");
            }, new HandlebarsTemplateEngine());

//            //get: show all heroes
            get("/heroes", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                List<Hero> heroes = Hero.all();
                model.put("hero", heroes);

                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());
        }
    }

