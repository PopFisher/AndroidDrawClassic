package popfisher.androiddrawclassic.samples;

/**
 * 将封装View的操作包装一下，然后给其他地方调用，View需要实现这个借口
 */

public interface IViewNotifyListener {

    void invalidateView();
}
