package intf;

import constant.StatusType;
import impl.action.Spell;

public interface GameUnit {

    int[] getLocation();

    GameUnit setLocation(int x, int y);

    GameUnit setHp(int hp);

    int getHp();

    GameUnit setMp(int mp);

    int getMp();

    int getLevel();

    GameUnit setLevel(int level);

    int getMoveSpeed();

    GameUnit setMoveSpeed(int move);

    StatusType getStatus();

    GameUnit setStatus(StatusType type);

    int getAttack();

    GameUnit setAttack(int attack);

    int getDefence();

    GameUnit setDefence(int defence);

    int getMaxHp();

    int getMaxMp();

    StatusType getType();

    GameUnit addSpell(Spell spell);

    GameUnit removeSpell(Spell spell);

    GameUnit removeSpell(int index);

    GameUnit setType(StatusType default_type);

    Spell[] getSpellList();

    GameUnit setSpellList(Spell list[]);

    GameUnit setMaxHp(int hp);

    GameUnit setMaxMp(int mp);

    GameUnit hurt(int damage);

    GameUnit heal(int heal);

    GameUnit move(int x, int y);

    GameUnit attack(GameUnit target);

    GameUnit spell(int index, GameUnit target);

    void onAttack();

    void onMove();

    void onSpell();

}
