package pl.michaloruba.imagequizz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int [] IMAGES = {
            R.drawable.ariel,
            R.drawable.bajka,
            R.drawable.bojka,
            R.drawable.bolek,
            R.drawable.braworka,
            R.drawable.chip_dale,
            R.drawable.daisy,
            R.drawable.diego,
            R.drawable.donald,
            R.drawable.filemon,
            R.drawable.garfield,
            R.drawable.gargamel,
            R.drawable.goofy,
            R.drawable.jerry,
            R.drawable.johnny_bravo,
            R.drawable.julian,
            R.drawable.klapouchy,
            R.drawable.kowalski,
            R.drawable.krzys,
            R.drawable.kubus_puchatek,
            R.drawable.lolek,
            R.drawable.malenstwo,
            R.drawable.maniek,
            R.drawable.miki,
            R.drawable.mini,
            R.drawable.mort,
            R.drawable.pantera,
            R.drawable.pluto,
            R.drawable.pocahontas,
            R.drawable.popeye,
            R.drawable.prosiaczek,
            R.drawable.pumba,
            R.drawable.reksio,
            R.drawable.rico,
            R.drawable.sid,
            R.drawable.simba,
            R.drawable.skaza,
            R.drawable.skipper,
            R.drawable.stich,
            R.drawable.szeregowy,
            R.drawable.timon,
            R.drawable.tom,
            R.drawable.uszatek,
            R.drawable.yogi
    };

    private final String[][] ANSWERS = {
            {"Ariel", "Lilia", "Nina", "Urszula"},
            {"Bajka", "Bójka", "Brawurka", "Bombka"},
            {"Bójka", "Bajka", "Bombka", "Brawurka"},
            {"Bolek", "Lolek", "Tolek", "Romek"},
            {"Brawurka", "Bójka", "Bajka", "Bombka"},
            {"Chip i Dale", "Alvin i Teodor", "Chip i Alvin", "Teodor i Szymon"},
            {"Daisy", "Dafnia", "Dzióbella", "Della"},
            {"Diego", "Tygrys", "Softi", "Samson"},
            {"Donald", "Kwacław", "Kwakier", "Kwalutek"},
            {"Filemon", "Bonifacy", "Felix", "Klakier"},
            {"Garfield", "Filemon", "Tom", "Klakier"},
            {"Gargamel", "Merlin", "Panoramiks", "Pan Twardowski"},
            {"Goofy", "Gilbert", "Gufus", "Gufek"},
            {"Jerry", "Tom", "Tweetie", "Buba"},
            {"Johnny Bravo", "Bunny Bravo", "Pops", "Mistrz Hamma"},
            {"Król Julian", "Maurice", "Mort", "Rysiek"},
            {"Kłapouchy", "Bąbel", "Gucio", "Fafik"},
            {"Kowalski", "Skipper", "Rico", "Szeregowy"},
            {"Krzyś", "Antoś", "Fifi", "Tomuś"},
            {"Kubuś Puchatek", "Miś Yogi", "Miś Uszatek", "Baloo"},
            {"Lolek", "Bolek", "Tolek", "Romek"},
            {"Maleństwo", "Kangurek", "Prosiaczek", "Kruszynek"},
            {"Maniek", "Louis", "Ela", "Itan"},
            {"Miki", "Toodles", "Gladiusz", "Bella"},
            {"Mini", "Midi", "Maxi", "Daisy"},
            {"Mort", "Maurice", "Król Julian", "Marlenka"},
            {"Różowa Pantera", "Nochal", "Konio", "Mrówek"},
            {"Pluto", "Goofy", "Percy", "Pongo"},
            {"Pocahontas", "Nakoma", "Kekata", "Meeko"},
            {"Popeye", "Bluto", "Wimpy", "Sweepy"},
            {"Prosiaczek", "Maleństwo", "Kłapouchy", "Krzyś"},
            {"Pumba", "Timon", "Simba", "Nala"},
            {"Reksio", "Trusty", "Tramp", "As"},
            {"Rico", "Skipper", "Szeregowy", "Kowalski"},
            {"Sid", "Diego", "Maniek", "Scrat"},
            {"Simba", "Nala", "Rafiki", "Shenzi"},
            {"Skaza", "Mufasa", "Ed", "Banzai"},
            {"Skipper", "Rico", "Szeregowy", "Kowalski"},
            {"Stich", "Lilo", "Jumba", "Pleakley"},
            {"Szeregowy", "Rico", "Skipper", "Kowalski"},
            {"Timon", "Pumba", "Rafiki", "Simba"},
            {"Tom", "Jerry", "Klakier", "Bonifacy"},
            {"Miś Uszatek", "Miś Yogi", "Kubuś Puchatek", "Baloo"},
            {"Miś Yogi", "Kubuś Puchatek", "Miś Yogi", "Baloo"}
    };

    private int currentQuestion;
    private int score;
    private List<Integer> drawnQuestions;
    private Random random;

    public Game() {
        drawnQuestions = new ArrayList<>();
        random = new Random();
        currentQuestion = 0;
        score = 0;
    }

    public int questionsAsked(){
        return drawnQuestions.size();
    }

    public int getScore(){
        return score;
    }

    public int getCurrentQuestionImage(){
        return IMAGES[currentQuestion];
    }

    public String getCurrentQuestionAnswer(int index){
        return ANSWERS[currentQuestion][index];
    }

    public void rollNextQuestion() {
        do {
            currentQuestion = random.nextInt(44);
        }
        while (drawnQuestions.contains(currentQuestion));
        drawnQuestions.add(currentQuestion);
    }

    public String getCorrectAnswer(){
        return ANSWERS[currentQuestion][0];
    }

    public void increaseScore() {
        score++;
    }
}