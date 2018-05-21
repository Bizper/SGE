package intf;

public interface Concept {

    int getID();

    Concept setID(int id);
    /**
     * 确认其实例是否被使用
     * @return true为被使用，false为未使用
     */
    boolean getUsed();

    /**
     * 被创建时调用的方法
     * @return 实例本身
     */
    Concept create();

    /**
     * 调用此方法来销毁此实例
     * @return 实例本身，无法被使用
     */
    Concept destroy();

    /**
     * 此方法将在被销毁时被调用
     */
    void onDestroy();

    /**
     * 此方法将在被创建时被调用
     */
    void onCreate();

    /**
     * 此方法将在状态被改变时被调用
     */
    void onFlush();

    /**
     * 此方法将在被绘制时被调用
     */
    void onPaint();

}
