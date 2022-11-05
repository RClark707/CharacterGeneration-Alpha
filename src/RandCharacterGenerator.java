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

    public static String randRace() {
        return raceArray[rand.nextInt(raceArray.length)];
    }

    public static String randName() {
        return nameArray[rand.nextInt(nameArray.length)];
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
            case "Artificer" -> {
                // we will use a random number based on the quantity of valid subclasses
                // in order to pick a subclass
                String[] subclasses0 = {
                        "Armorer",
                        "Alchemist",
                        "Battle Smith",
                        "Artillerist",
                };
                charSubclass = subclasses0[rand.nextInt(subclasses0.length)];
            }
            //Barbarian
            case "Barbarian" -> {
                String[] subclasses1 = {
                        "Path of the Ancestral Guardian",
                        "Path of the Battlerager",
                        "Path of the Beast",
                        "Path of the Berserker",
                        "Path of the Storm Herald",
                        "Path of the Totem Warrior",
                        "Path of Wild Magic",
                        "Path of the Zealot",
                };
                charSubclass = subclasses1[rand.nextInt(subclasses1.length)];
            }
            //Bard
            case "Bard" -> {
                String[] subclasses2 = {
                        "College of Creation",
                        "College of Eloquence",
                        "College of Glamour",
                        "College of Lore",
                        "College of Spirits",
                        "College of Swords",
                        "College of Valor",
                        "College of Whispers",
                };
                charSubclass = subclasses2[rand.nextInt(subclasses2.length)];
            }
            //Cleric
            case "Cleric" -> {
                String[] subclasses3 = {
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
                charSubclass = subclasses3[rand.nextInt(subclasses3.length)];
            }
            //Druid
            case "Druid" -> {
                String[] subclasses4 = {
                        "Circle of Dreams",
                        "Circle of the Land",
                        "Circle of the Moon",
                        "Circle of the Shepherd",
                        "Circle of Spores",
                        "Circle of Stars",
                        "Circle of Wildfire",
                };
                charSubclass = subclasses4[rand.nextInt(subclasses4.length)];
            }
            //Fighter
            case "Fighter" -> {
                String[] subclasses5 = {
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
                charSubclass = subclasses5[rand.nextInt(subclasses5.length)];
            }
            //Monk
            case "Monk" -> {
                String[] subclasses6 = {
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
                charSubclass = subclasses6[rand.nextInt(subclasses6.length)];
            }
            //Paladin
            case "Paladin" -> {
                String[] subclasses7 = {
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
                charSubclass = subclasses7[rand.nextInt(subclasses7.length)];
            }
            //Ranger
            case "Ranger" -> {
                String[] subclasses8 = {
                        "Beast Master Conclave",
                        "Fey Wanderer",
                        "Gloom Stalker Conclave",
                        "Horizon Walker Conclave",
                        "Hunter Conclave",
                        "Monster Slayer Conclave",
                        "Swarmkeeper",
                        "Drakewarden",
                };
                charSubclass = subclasses8[rand.nextInt(subclasses8.length)];
            }
            //Rogue
            case "Rogue" -> {
                String[] subclasses9 = {
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
                charSubclass = subclasses9[rand.nextInt(subclasses9.length)];
            }
            //Sorcerer
            case "Sorcerer" -> {
                String[] subclasses10 = {
                        "Aberrant Mind",
                        "Clockwork Soul",
                        "Draconic Bloodline",
                        "Divine Soul",
                        "Shadow Magic",
                        "Storm Sorcery",
                        "Wild Magic",
                };
                charSubclass = subclasses10[rand.nextInt(subclasses10.length)];
            }
            //Warlock
            case "Warlock" -> {
                String[] subclasses11 = {
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
                charSubclass = subclasses11[rand.nextInt(subclasses11.length)];
            }
            //Wizard
            case "Wizard" -> {
                String[] subclasses12 = {
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
                charSubclass = subclasses12[rand.nextInt(subclasses12.length)];
            }
            default -> System.out.println("Looks like there is an error!");
        }

        return charSubclass;
    }

    public static String[] randSkillArray(int numSkills) {
        String[] skillList = new String[numSkills];
        boolean notUnique = false;
        int count;

        do {
            for (int i = 0; i < numSkills; ++i) {
                skillList[i] = RandCharacterGenerator.allProf[rand.nextInt((18))];
            }
            // This checks to see if there are any duplicates, but it is unnaturally slow, and sometimes seems to not execute at > 4 proficiencies
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
                return new String[]{"Empty"};
            }

        }
    }

    public static String randStat() {
        return statNames[rand.nextInt(statNames.length)];
    }
}

