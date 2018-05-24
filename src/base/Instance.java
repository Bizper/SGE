package base;

import constant.DefaultConstant;
import constant.StatusType;
import impl.action.Spell;
import intf.Concept;
import intf.GameUnit;
import service.ConceptFactory;

import java.util.Arrays;

public abstract class Instance implements Concept, GameUnit, DefaultConstant {

    protected int hp = DEFAULT_HP;
    protected int maxHp = DEFAULT_HP;
    protected int mp = DEFAULT_MP;
    protected int maxMp = DEFAULT_MP;
    protected int attack = DEFAULT_ATTACK;
    protected int defence = DEFAULT_DEFENCE;
    protected StatusType default_type = DEFAULT_STATUS;
    protected String name = DEFAULT_NAME;
    protected int level = DEFAULT_LEVEL;
    protected int moveSpeed = DEFAULT_MOVE_SPEED;
    protected int x = 0;
    protected int y = 0;
    protected Spell spell_list[] = new Spell[MAX_SPELL_NUMBER];

    protected int ID = DEFAULT_ID;

    protected boolean isUsed = false;//判断当前实例是否被使用

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public GameUnit setAttack(int attack) {
        if(attack < MIN_ATTACK) attack = MIN_ATTACK;
        this.attack = attack;
        onFlush();
        return this;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public GameUnit setDefence(int defence) {
        if(defence < MIN_DEFENCE) defence = MIN_DEFENCE;
        this.defence = defence;
        onFlush();
        return this;
    }

    @Override
    public int[] getLocation() {
        return new int[]{x, y};
    }

    @Override
    public GameUnit setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        onFlush();
        return this;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public int getMaxMp() {
        return maxMp;
    }

    @Override
    public GameUnit setMaxHp(int hp) {
        double percent = (this.hp / maxHp);
        this.maxHp = hp;
        setHp((int)(maxHp * percent));
        onFlush();
        return this;
    }

    @Override
    public GameUnit setMaxMp(int mp) {
        double percent = (this.mp / maxMp);
        this.maxMp = mp;
        setMp((int)(maxMp * percent));
        onFlush();
        return this;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public GameUnit setHp(int hp) {
        if(hp < MIN_HP) hp = MIN_HP;
        if(hp > maxHp) hp = maxHp;
        this.hp = hp;
        onFlush();
        return this;
    }

    @Override
    public GameUnit setMp(int mp) {
        if(mp < MIN_MP) mp = MIN_MP;
        if(mp > maxMp) mp = maxMp;
        this.mp = mp;
        onFlush();
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Concept setName(String name) {
        this.name = name;
        onFlush();
        return this;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public GameUnit setLevel(int level) {
        if(level < MIN_LEVEL) level = MIN_LEVEL;
        if(level > MAX_LEVEL) level = MAX_LEVEL;
        this.level = level;
        onFlush();
        return this;
    }

    @Override
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public GameUnit setMoveSpeed(int move) {
        if(move < MIN_MOVE_SPEED) move = MIN_MOVE_SPEED;
        if(move > MAX_MOVE_SPEED) move = MAX_MOVE_SPEED;
        moveSpeed = move;
        onFlush();
        return this;
    }

    @Override
    public StatusType getStatus() {
        return default_type;
    }

    @Override
    public GameUnit setStatus(StatusType type) {
        this.default_type = type;
        onFlush();
        return this;
    }

    @Override
    public boolean getUsed() {
        return isUsed;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public Concept setID(int id) {
        ID = id;
        onFlush();
        return this;
    }

    @Override
    public Concept create() {
        this.isUsed = true;
        onCreate();
        return this;
    }

    @Override
    public Concept destroy() {
        this.isUsed = false;
        onDestroy();
        ConceptFactory.receive(this);
        return this;
    }

    @Override
    public GameUnit hurt(int damage) {
        damage = (int)(damage - damage * (DEFAULT_DEFENCE_SENSOR * getDefence()));
        setHp(getHp() - damage);
        return this;
    }

    @Override
    public GameUnit heal(int heal) {
        setHp(getHp() + heal);
        return this;
    }

    @Override
    public GameUnit move(int x, int y) {
        setLocation(x, y);
        onMove();
        return this;
    }

    @Override
    public GameUnit attack(GameUnit target) {
        target.hurt(getAttack());
        onAttack();
        return this;
    }

    @Override
    public GameUnit spell(int num, GameUnit target) {
        if(num < 0 || num >= MAX_SPELL_NUMBER || spell_list[num] == null) return this;
        spell_list[num].cast(this, target);
        onSpell();
        return this;
    }

    @Override
    public void onDestroy() { }

    @Override
    public void onCreate() { }

    @Override
    public void onFlush() { }

    @Override
    public void onPaint() { }

    @Override
    public void onAttack() { }

    @Override
    public void onMove() { }

    @Override
    public void onSpell() { }

    @Override
    public String toString() {
        return "Instance [" +
                "hp=" + hp +
                ", maxHp=" + maxHp +
                ", mp=" + mp +
                ", maxMp=" + maxMp +
                ", attack=" + attack +
                ", defence=" + defence +
                ", default_type=" + default_type +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", moveSpeed=" + moveSpeed +
                ", x=" + x +
                ", y=" + y +
                ", spell_list=" + (spell_list == null ? null : Arrays.asList(spell_list)) +
                ", ID=" + ID +
                ", isUsed=" + isUsed +
                ']';
    }
}