package intf;

import constant.EventType;
import constant.StatusType;
import impl.Item;
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

    int[] getPack();

    int getPackSize();

    EventType addItem(int id, int num);

    GameUnit setPackSize(int packSize);

    EventType addItem(int id);

    EventType removeItem(int num);

    GameUnit setStatus(StatusType type);

    int getAttack();

    GameUnit setOwner(GameUnit unit);

    GameUnit getOwner();

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

    void onAttack(EventType type);

    void onMove(EventType type);

    void onSpell(EventType type);

    void onHurt(EventType type);

    void onHeal(EventType type);

}
