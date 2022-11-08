import java.util.Random;

/**
 * @author rclar
 *
 */
public class RandCharacterGenerator {
    static Random rand = new Random();
    // Used often in the random methods

    /**
     *
     *
     */

    // This array is central to the entire class, trust me
    private static final String[] classArray = {
            "Artificer",
            "Barbarian",
            "Bard",
            "Cleric",
            "Druid",
            "Fighter",
            "Monk",
            "Paladin",
            "Ranger",
            "Rogue",
            "Sorcerer",
            "Warlock",
            "Wizard",
    };

    private static final String[] artificerSubclasses = {
            "Armorer",
            "Alchemist",
            "Battle Smith",
            "Artillerist",
    };

    private static final String[] barbarianSubclasses = {
            "Path of the Ancestral Guardian",
            "Path of the Battlerager",
            "Path of the Beast",
            "Path of the Berserker",
            "Path of the Storm Herald",
            "Path of the Totem Warrior",
            "Path of Wild Magic",
            "Path of the Zealot",
    };

    private static final String[] bardSubclasses = {
            "College of Creation",
            "College of Eloquence",
            "College of Glamour",
            "College of Lore",
            "College of Spirits",
            "College of Swords",
            "College of Valor",
            "College of Whispers",
    };

    private static final String[] clericSubclasses = {
            "Arcana Domain",
            "Death Domain",
            "Forge Domain",
            "Grave Domain",
            "Knowledge Domain",
            "Life Domain",
            "Light Domain",
            "Nature Domain",
            "Order Domain",
            "Peace Domain",
            "Tempest Domain",
            "Trickery Domain",
            "Twilight Domain",
            "War Domain",
    };

    private static final String[] druidSubclasses = {
            "Circle of Dreams",
            "Circle of the Land",
            "Circle of the Moon",
            "Circle of the Shepherd",
            "Circle of Spores",
            "Circle of Stars",
            "Circle of Wildfire",
    };

    private static final String[] fighterSubclasses = {
            "Arcane Archer",
            "Banneret",
            "Battle Master",
            "Cavalier",
            "Champion",
            "Echo Knight",
            "Eldritch Knight",
            "Psi Warrior",
            "Rune Knight",
            "Samurai",
    };

    private static final String[] monkSubclasses = {
            "Way of Mercy",
            "Way of the Astral Self",
            "Way of the Drunken Master",
            "Way of the Four Elements",
            "Way of the Kensei",
            "Way of the Long Death",
            "Way of the Open Hand",
            "Way of Shadow",
            "Way of the Sun Soul",
            "Way of the Ascendant Dragon",
    };

    private static final String[] paladinSubclasses = {
            "Oath of the Ancients",
            "Oath of Conquest",
            "Oath of the Crown",
            "Oath of Devotion",
            "Oath of Glory",
            "Oath of Redemption",
            "Oath of Vengeance",
            "Oath of the Watchers",
            "Oathbreaker",
    };

    private static final String[] rangerSubclasses = {
            "Beast Master Conclave",
            "Fey Wanderer",
            "Gloom Stalker Conclave",
            "Horizon Walker Conclave",
            "Hunter Conclave",
            "Monster Slayer Conclave",
            "Swarmkeeper",
            "Drakewarden",
    };

    private static final String[] rogueSubclasses = {
            "Arcane Trickster",
            "Assassin",
            "Inquisitive",
            "Mastermind",
            "Phantom",
            "Scout",
            "Soulknife",
            "Swashbuckler",
            "Thief",
    };

    private static final String[] sorcererSubclasses = {
            "Aberrant Mind",
            "Clockwork Soul",
            "Draconic Bloodline",
            "Divine Soul",
            "Shadow Magic",
            "Storm Sorcery",
            "Wild Magic",
    };

    private static final String[] warlockSubclasses = {
            "Archfey",
            "Celestial",
            "Fathomless",
            "Fiend",
            "The Genie",
            "Great Old One",
            "Hexblade",
            "Undead",
            "Undying",
    };

    private static final String[] wizardSubclasses = {
            "School of Abjuration",
            "School of Bladesinging",
            "School of Chronurgy",
            "School of Conjuration",
            "School of Divination",
            "School of Enchantment",
            "School of Evocation",
            "School of Graviturgy",
            "School of Illusion",
            "School of Necromancy",
            "Order of Scribes",
            "School of Transmutation",
            "School of War Magic",
    };

    private static final String[] statNames = {
            "Strength",
            "Dexterity",
            "Constitution",
            "Intelligence",
            "Wisdom",
            "Charisma",
    };

    private static final String[] artificerProf = {
            "two", "Arcana", "History", "Investigation", "Medicine", "Nature", "Perception", "Sleight of Hand",
    };

    private static final String[] barbarianProf = {
            "two", "Animal Handling", "Athletics", "Intimidation", "Nature", "Perception", "Survival",
    };

    private static final String[] clericProf = {
            "two", "History", "Insight", "Medicine", "Persuasion", "Religion",
    };

    private static final String[] druidProf = {
            "two", "Arcana", "Animal Handling", "Insight", "Medicine", "Nature", "Perception", "Religion", "Survival",
    };

    private static final String[] fighterProf = {
            "two", "Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival",
    };

    private static final String[] monkProf = {
            "two", "Acrobatics", "Athletics", "History", "Insight", "Religion", "Stealth"
    };

    private static final String[] paladinProf = {
            "two", "Athletics", "Insight", "Intimidation", "Medicine", "Persuasion", "Religion",
    };

    private static final String[] rangerProf = {
            "three", "Animal Handling", "Athletics", "Insight", "Investigation", "Nature", "Perception", "Stealth", "Survival",
    };

    private static final String[] rogueProf = {
            "four", "Acrobatics", "Athletics", "Deception", "Insight", "Intimidation", "Investigation", "Perception", "Performance", "Persuasion", "Sleight of Hand", "Stealth",
    };

    private static final String[] sorcererProf = {
            "two", "Arcana", "Deception", "Insight", "Intimidation", "Persuasion", "Religion"
    };

    private static final String[] warlockProf = {
            "two", "Arcana", "Deception", "History", "Intimidation", "Investigation", "Nature", "Religion"
    };

    private static final String[] wizardProf = {
            "two", "Arcana", "History", "Insight", "Investigation", "Medicine", "Religion"
    };

    public static final String[] allProf = {
            "Acrobatics",
            "Animal Handling",
            "Arcana",
            "Athletics",
            "Deception",
            "History",
            "Insight",
            "Intimidation",
            "Investigation",
            "Medicine",
            "Nature",
            "Perception",
            "Performance",
            "Persuasion",
            "Religion",
            "Sleight of Hand",
            "Stealth",
            "Survival",
    };

    public static final String[] raceArray = {
            "Aasimar",
            "Dhampir",
            "Dragonborn",
            "Dwarf",
            "Elf",
            "Fairy",
            "Genasi",
            "Gnome",
            "Goliath",
            "Half-Elf",
            "Half-Orc",
            "Halfling",
            "Harengon",
            "Hexblood",
            "Human",
            "Nymph",
            "Reborn",
            "Tabaxi",
            "Tiefling",
    };

    public static final String[] nameArray = {
            "Arkus",
            "Auric",
            "Avren",
            "Bassuras",
            "Bennedict",
            "Borick",
            "Ira",
            "Kos",
            "Logaine",
            "Magnus",
            "Omen",
            "Philip",
            "Proteus",
            "Shalatar",
            "Val",
    };

    public static final String[] backgroundArray = {
            "Acolyte",
            "Anthropologist",
            "Archaeologist",
            "Athlete",
            "Charlatan",
            "City Watch",
            "Clan Crafter",
            "Cloistered Scholar",
            "Courtier",
            "Criminal",
            "Entertainer",
            "Faceless",
            "Faction Agent",
            "Far Traveler",
            "Feylost",
            "Fisher",
            "Folk Hero",
            "Gladiator",
            "Guild Artisan",
            "Guild Merchant",
            "Haunted One",
            "Hermit",
            "House Agent",
            "Inheritor",
            "Investigator",
            "Knight",
            "Knight of the Order",
            "Marine",
            "Mercenary Veteran",
            "Noble",
            "Outlander",
            "Pirate",
            "Sage",
            "Sailor",
            "Shipwright",
            "Smuggler",
            "Soldier",
            "Spy",
    };

    private static final String[] schoolsOfMagic = {
            "Abjuration",
            "Conjuration",
            "Divination",
            "Enchantment",
            "Evocation",
            "Illusions",
            "Necromancy",
            "Transmutation",
    };

    public static void printOptions(String desiredArrayName, String charClass) {
        switch (desiredArrayName) {
            case "Class" -> {
                System.out.print("\n");
                System.out.println("Here are the valid Classes:");
                for (String s : classArray) {
                    System.out.println(s);
                }
                System.out.print("\n");
            }
            case "Subclass" -> {
                System.out.print("\n");
                System.out.println("Here are the valid Subclasses:");
                // For some reason, IntelliJ really doesn't like that this could have a null pointer, but I don't believe it can
                if (RandCharacterGenerator.returnSubclassArray(charClass) != null) {
                    for (String s : RandCharacterGenerator.returnSubclassArray(charClass)) {
                        System.out.println(s);
                    }
                }
                System.out.print("\n");
            }
            case "Race" -> {
                System.out.print("\n");
                System.out.println("Here are examples of valid Races (but you can enter whatever you want):");
                for (String s : raceArray) {
                    System.out.println(s);
                }
                System.out.print("\n");
            }
            case "Background" -> {
                System.out.print("\n");
                System.out.println("Here are examples of valid Backgrounds (but you can enter whatever you want):");
                for (String s : backgroundArray) {
                    System.out.println(s);
                }
                System.out.print("\n");
            }
            case "Schools of Magic" -> {
                System.out.print("\n");
                System.out.println("Here are examples of valid Schools of Magic (but you can enter whatever you want):");
                for (String s : schoolsOfMagic) {
                    System.out.println(s);
                }
                System.out.print("\n");
            }
        }
    }

    public static String[] returnSubclassArray(String charClass) {
        switch (charClass) {
            case "Artificer" -> {
                return artificerSubclasses;
            }
            case "Barbarian" -> {
                return barbarianSubclasses;
            }
            case "Bard" -> {
                return bardSubclasses;
            }
            case "Cleric" -> {
                return clericSubclasses;
            }
            case "Druid" -> {
                return druidSubclasses;
            }
            case "Fighter" -> {
                return fighterSubclasses;
            }
            case "Monk" -> {
                return monkSubclasses;
            }
            case "Paladin" -> {
                return paladinSubclasses;
            }
            case "Ranger" -> {
                return rangerSubclasses;
            }
            case "Rogue" -> {
                return rogueSubclasses;
            }
            case "Sorcerer" -> {
                return sorcererSubclasses;
            }
            case "Warlock" -> {
                return warlockSubclasses;
            }
            case "Wizard" -> {
                return wizardSubclasses;
            }
            default -> {
                return statNames;
            }
        }
    }

    public static String randName() {
        return nameArray[rand.nextInt(nameArray.length)];
    }
    public static String randRace() {
        return raceArray[rand.nextInt(raceArray.length)];
    }
    // Generates a random character class,
    public static String randCharClass() {
        return classArray[rand.nextInt(classArray.length)];
    }
    // This will give us a random subclass based on the class you are
    public static String randCharSubclass(String charClass) {
        String charSubclass = "";
        // This is the switch I mentioned before, We use an ID to declare what class you are
        // And then we pick a random subclass from an array of valid ones
        // It would be nice to use a List(?) or 2D Array, but I don't know how
        // This can be replaced with an 'enhanced switch.' I'm interested in what that is.
        // An enhanced switch cleans it up a little, removing breaks and surrounding each case in braces
        switch (charClass) {
            //Artificer
            case "Artificer" -> // we will use a random number based on the quantity of valid subclasses
                // in order to pick a subclass
                    charSubclass = artificerSubclasses[rand.nextInt(artificerSubclasses.length)];
            //Barbarian
            case "Barbarian" -> charSubclass = barbarianSubclasses[rand.nextInt(barbarianSubclasses.length)];
            //Bard
            case "Bard" -> charSubclass = bardSubclasses[rand.nextInt(bardSubclasses.length)];
            //Cleric
            case "Cleric" -> charSubclass = clericSubclasses[rand.nextInt(clericSubclasses.length)];
            //Druid
            case "Druid" -> charSubclass = druidSubclasses[rand.nextInt(druidSubclasses.length)];
            //Fighter
            case "Fighter" -> charSubclass = fighterSubclasses[rand.nextInt(fighterSubclasses.length)];
            //Monk
            case "Monk" -> charSubclass = monkSubclasses[rand.nextInt(monkSubclasses.length)];
            //Paladin
            case "Paladin" -> charSubclass = paladinSubclasses[rand.nextInt(paladinSubclasses.length)];
            //Ranger
            case "Ranger" -> charSubclass = rangerSubclasses[rand.nextInt(rangerSubclasses.length)];
            //Rogue
            case "Rogue" -> charSubclass = rogueSubclasses[rand.nextInt(rogueSubclasses.length)];
            //Sorcerer
            case "Sorcerer" -> charSubclass = sorcererSubclasses[rand.nextInt(sorcererSubclasses.length)];
            //Warlock
            case "Warlock" -> charSubclass = warlockSubclasses[rand.nextInt(warlockSubclasses.length)];
            //Wizard
            case "Wizard" -> charSubclass = wizardSubclasses[rand.nextInt(wizardSubclasses.length)];
            default -> System.out.println("Looks like there is an error!");
        }
        return charSubclass;
    }

    public static String randBackground() {return backgroundArray[rand.nextInt(backgroundArray.length)];}

    public static String[] randSkillArray(int numSkills) {
        String[] skillList = new String[numSkills];
        boolean notUnique;
        int count;

        do {
            notUnique = false;
            // This one line saved it all
            for (int i = 0; i < numSkills; ++i) {
                skillList[i] = RandCharacterGenerator.allProf[rand.nextInt((allProf.length))];
            }
            for (String s : skillList) {
                count = 0;
                for (String k : skillList) {
                    if (s.equals(k)) {
                        count++;
                    }
                    if (count > 1) {
                        notUnique = true;
                        break;
                    }
                }
                if (notUnique) {
                    break;
                }
            }
        } while (notUnique);

        return skillList;
    }

    public static String[] getClassSkillArray(String charClass) {
        switch (charClass) {
            case "Artificer" -> {
                return artificerProf;
            }
            case "Barbarian" -> {
                return barbarianProf;
            }
            case "Cleric" -> {
                return clericProf;
            }
            case "Druid" -> {
                return druidProf;
            }
            case "Fighter" -> {
                return fighterProf;
            }
            case "Monk" -> {
                return monkProf;
            }
            case "Paladin" -> {
                return paladinProf;
            }
            case "Ranger" -> {
                return rangerProf;
            }
            case "Rogue" -> {
                return rogueProf;
            }
            case "Sorcerer" -> {
                return sorcererProf;
            }
            case "Warlock" -> {
                return warlockProf;
            }
            case "Wizard" -> {
                return wizardProf;
            }
            default -> {
                return new String[]{"Combat", "Artistry"};
            }
        }
    }

    public static String randStat() {
        return statNames[rand.nextInt(statNames.length)];
    }
}

