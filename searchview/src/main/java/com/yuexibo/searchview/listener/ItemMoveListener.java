package com.yuexibo.searchview.listener;

public interface ItemMoveListener {
    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);
}
