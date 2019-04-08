package e.plass.acceuilwayfinding.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import e.plass.acceuilwayfinding.R;

public class Util {
    private static ArrayList<Ecole>     ecoles     = new ArrayList<>();
    private static ArrayList<Formation> formations = new ArrayList<>();
    private static List<SearchType> searchTypes = new ArrayList<>();
    private static List<Concours>concours = new ArrayList<>();
    private static Formation currentFormation;
    private static Ecole currentEcole;

    public static List<Concours> getConcours() {
        return concours;
    }

    public static void setConcours(List<Concours> concours) {
        Util.concours = concours;
    }

    public static Ecole getCurrentEcole() {
        return currentEcole;
    }

    public static void setCurrentEcole(Ecole currentEcole) {
        Util.currentEcole = currentEcole;
    }

    public static Formation getCurrentFormation() {
        return currentFormation;
    }

    public static void setCurrentFormation(Formation currentFormation) {
        Util.currentFormation = currentFormation;
    }

    public Util(){
        ecoles.clear();
        Calendar c = Calendar.getInstance();
        c.set(2010,01,01);
        ecoles.add(new Ecole(1,12f,"Catholic University of Cameroon","ucc.png", c,"Bamenda","",""
        ,"Créée en 2010, l’Université catholique du Cameroun est un établissement d’enseignement supérieur privé sans but lucratif situé dans le milieu urbain de la ville moyenne de Bamenda (population de 500 000 à 1 000 000 habitants), région du Nord-Ouest. Officiellement accréditée et / ou reconnue par le Ministère de l’Enseignement Supérieur du Cameroun, l’Université Catholique du Cameroun (CATUC) est une institution d’enseignement supérieur mixte formellement affiliée à la religion chrétienne-catholique. L’Université catholique du Cameroun (CATUC) propose des cours et des programmes menant à des diplômes d’enseignement supérieur officiellement reconnus dans plusieurs domaines d’études."));

        c.set(1971,06,04);
        ecoles.add(new Ecole(2,10f,"École nationale supérieure polytechnique de Yaoundé","ensp", c,"Yaoundé","",""
        ,"L'École Nationale Supérieure Polytechnique de Yaoundé (en abrégée ENSP) est fondée le 04 juin 19711 avec pour objectifs : former des cadres (ingénieurs), renforcer la recherche pour l'émergence du Cameroun. Elle est une composante de l'Université de Yaoundé 13. Située dans le quartier Ngoa Ekelle, aujourd'hui elle assure la formation d'ingénieurs de conception (bac +5). Jusqu'en 2015, elle avait formée plus de 3300 ingénieurs. "));

        c.set(2002,01,01);
        ecoles.add(new Ecole(3,15f,"Institut universitaire de la cote","iuc", c,"Douala","","Paul GUIMEZAP"
        ,"L’Institut Universitaire de la Côte (IUC) est une grande institution privée d’enseignement supérieur dont la vocation est la formation intellectuelle et humaine des étudiants de nationalité Camerounaise et étrangère aux cycles de brevet de technicien supérieur, de licence professionnelle, de master et d’ingénieur, pour le développement des entreprises et de l’entreprenariat en rapport avec l’évolution du monde industriel, technologique et des affaires.\n" +
                "\n" +
                "Crée en 2002 à Logbessou (Douala 5ème), pour devenir un pôle d’excellence en Afrique et, l’institution de référence auprès des entreprises, l’IUC s’adresse aux candidats diplômés au moins d’un baccalauréat des séries industrielles, techniques, commerciales, scientifiques et littéraires, qui ont pour ambition, de devenir agent de maîtrise, cadre supérieur ou ingénieur - entrepreneur."));


        c.set(1993,01,01);
        ecoles.add(new Ecole(4,16f,"Institut universitaire du Golfe de Guinée","iug", c,"Douala","",""
        ,"Fondé en 1993, l'Institut Universitaire du Golfe de Guinée est un groupe de trois établissements d’enseignement supérieur basé dans le même campus à Bassa-Douala.\n" +
                "\n" +
                "Spécialisée dans les domaines du Commerce et de la gestion, de la communication et de l’information, de l’industrie et des nouvelles technologies et de la formation para-médicale, elle est l’une des plus importantes Institutions Privées d’Enseignement Supérieur au Cameroun."));


        c.set(2010,01,01);
        ecoles.add(new Ecole(5,8f,"Université de Douala","ud", c,"Douala","",""
        ,"Créée par le décret présidentiel n° 93/030 du 19 janvier 1993, l'université de Douala (UDla) a hérité des structures de l’École supérieure des sciences économiques et commerciales et de l’École normale d'enseignement technique4.\n" +
                "\n" +
                "En 2010, sa cité universitaire a été le siège d'une épidémie de choléra provoquée par la pollution des eaux de son puits. "));



        c.set(2010,01,01);
        ecoles.add(new Ecole(6,8f,"Ecole des Infirmiers Diplômés d’Etat","eide", c,"Ngaoundéré","",""
        ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));

        c.set(2010,01,01);
        ecoles.add(new Ecole(7,8f,"Ecole Supérieure des Travaux Publics (Buea)","estp", c,"Buea","",""
        ,"L’ENSTP assure la formation initiale des techniciens supérieur , des techniciens (niveau BAC et probatoire) à Buéa."));


        //------------------------------------------------------------
        formations.clear();
        ArrayList<String> d = new ArrayList<String>();
        d.add("Formation");
        d.add("Formation");
        d.add("Formation");
        d.add("Formation");
        d.add("Formation");
        d.add("Formation");
        formations.add(new Formation(1,"Informatique",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"info"));
        formations.add(new Formation(2,"Business",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"busi"));
        formations.add(new Formation(3,"Droit",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"dr"));
        formations.add(new Formation(4,"Langue",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"ln"));
        formations.add(new Formation(5,"Comptabilité",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"compta"));
        formations.add(new Formation(6,"Architecture",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"arch"));

        formations.add(new Formation(7,"Santé",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"sn"));

        formations.add(new Formation(8,"Mathématique",d," est un domaine d'activité scientifique, technique et industriel concernant le traitement automatique de l'information par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.\n" +
                "\n" +
                "Ces champs d'application peuvent être séparés en deux branches, l'une, de nature théorique, qui concerne la définition de concepts et modèles, et l'autre, de nature pratique, qui s'intéresse aux techniques concrètes de mise en œuvre. Certains domaines de l'informatique peuvent être très abstraits, comme la complexité algorithmique, et d'autres peuvent être plus proches d'un public profane. Ainsi, la théorie des langages demeure un domaine davantage accessible aux professionnels formés (description des ordinateurs et méthodes de programmation), tandis que les métiers liés aux interfaces homme-machine sont accessibles à un plus large public. ",
                15.7f,"ma"));

        //------------------------------------searchType
        searchTypes.clear();
        searchTypes.add(new SearchType("Les mieux notées", R.drawable.ic_favori_icons_plan));
        searchTypes.add(new SearchType("Spécialité", R.drawable.ic_speciliste_icons_plan));
        searchTypes.add(new SearchType("Ecole", R.drawable.ic_annuaire_icons_plan_icons_plan));
        searchTypes.add(new SearchType("Formation", R.drawable.ic_formations_icons_plan_icons_plan));
        searchTypes.add(new SearchType("Débouchées", R.drawable.ic_jobs_icons_plan));
        searchTypes.add(new SearchType("Recherche avancée", R.drawable.ic_rechercheavance_icons_plan));

        //***************************************************** Concours
        concours.clear();
        concours.add(new Concours("FGI",new Date(2019,12,20),new Date(2019,12,15)));
        concours.add(new Concours("Polytech",new Date(2019,06,2),new Date(2019,06,9)));
        concours.add(new Concours("Police",new Date(2019,5,20),new Date(2019,5,15)));
        concours.add(new Concours("IUT BANDJOUN",new Date(2019,12,4),new Date(2019,12,10)));
        concours.add(new Concours("IUC",new Date(2019,8,20),new Date(2019,8,25)));

    }

    public static List<SearchType> getSearchTypes() {
        return searchTypes;
    }

    public static void setSearchTypes(List<SearchType> searchTypes) {
        Util.searchTypes = searchTypes;
    }

    public static ArrayList<Ecole> getEcoles() {
        return ecoles;
    }

    public static void setEcoles(ArrayList<Ecole> ecoles) {
        Util.ecoles = ecoles;
    }

    public static ArrayList<Formation> getFormations() {
        return formations;
    }

    public static void setFormations(ArrayList<Formation> formations) {
        Util.formations = formations;
    }
}
