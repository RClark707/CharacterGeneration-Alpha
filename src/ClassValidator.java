public class ClassValidator {

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

    private static final String[] subclasses0 = {
            "Armorer",
            "Alchemist",
            "Battle Smith",
            "Artillerist",
    };

    private static final String[] subclasses1 = {
            "Path of the Ancestral Guardian",
            "Path of the Battlerager",
            "Path of the Beast",
            "Path of the Berserker",
            "Path of the Storm Herald",
            "Path of the Totem Warrior",
            "Path of Wild Magic",
            "Path of the Zealot",
    };

    private static final String[] subclasses2 = {
            "College of Creation",
            "College of Eloquence",
            "College of Glamour",
            "College of Lore",
            "College of Spirits",
            "College of Swords",
            "College of Valor",
            "College of Whispers",
    };

    private static final String[] subclasses3 = {
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

    private static final String[] subclasses4 = {
            "Circle of Dreams",
            "Circle of the Land",
            "Circle of the Moon",
            "Circle of the Shepherd",
            "Circle of Spores",
            "Circle of Stars",
            "Circle of Wildfire",
    };

    private static final String[] subclasses5 = {
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

    private static final String[] subclasses6 = {
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

    private static final String[] subclasses7 = {
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

    private static final String[] subclasses8 = {
            "Beast Master Conclave",
            "Fey Wanderer",
            "Gloom Stalker Conclave",
            "Horizon Walker Conclave",
            "Hunter Conclave",
            "Monster Slayer Conclave",
            "Swarmkeeper",
            "Drakewarden",
    };

    private static final String[] subclasses9 = {
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

    private static final String[] subclasses10 = {
            "Aberrant Mind",
            "Clockwork Soul",
            "Draconic Bloodline",
            "Divine Soul",
            "Shadow Magic",
            "Storm Sorcery",
            "Wild Magic",
    };

    private static final String[] subclasses11 = {
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

    private static final String[] subclasses12 = {
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

    public static boolean isValidClass(String charClass) {
        boolean valid = false;

        // Here we make sure the first letter is Capitalized
        if (!(Character.isUpperCase(charClass.charAt(0)))) {
            charClass = charClass.substring(0, 1).toUpperCase() + charClass.substring(1);
        }

        for (String s : classArray) {
            if (charClass.equals(s) || charClass.equals("Random")) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public static boolean isValidSubclass(String subclass, String charClass) {
        boolean valid = false;

        if (!(Character.isUpperCase(charClass.charAt(0)))) {
            charClass = charClass.substring(0, 1).toUpperCase() + charClass.substring(1);
        }

        // CAN I USE A GIANT FOR LOOP?
        switch (charClass) {
            case "Artificer" -> {
                for (String s : subclasses0) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Barbarian" -> {
                for (String s : subclasses1) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Bard" -> {
                for (String s : subclasses2) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Cleric" -> {
                for (String s : subclasses3) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Druid" -> {
                for (String s : subclasses4) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Fighter" -> {
                for (String s : subclasses5) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Monk" -> {
                for (String s : subclasses6) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Paladin" -> {
                for (String s : subclasses7) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Ranger" -> {
                for (String s : subclasses8) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Rogue" -> {
                for (String s : subclasses9) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Sorcerer" -> {
                for (String s : subclasses10) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Warlock" -> {
                for (String s : subclasses11) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Wizard" -> {
                for (String s : subclasses12) {
                    if (subclass.equals(s)) {
                        valid = true;
                        break;
                    }
                }
            }
            case "Random" -> valid = true;
            default -> {
                // do nothing
            }

        } // end of switch

        return valid;
    } // end of method

    public static boolean isValidStat(String statName) {
        for (String s : statNames) {
            if (statName.equals(s)) {
                return true;
            }
        }
        return false;
    }

}
