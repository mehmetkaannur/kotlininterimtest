package textfiles;

import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

final class LazyTextFile implements TextFile {
    TextFile target;
    Boolean isPendingInsertion = false;

    Pair<Integer, String> pending;

    public LazyTextFile(TextFile target) {
        this.target = target;
    }
    @Override
    public int getLength() {
        if (isPendingInsertion) {
            target.insertText(pending.getFirst(), pending.getSecond());
            isPendingInsertion = false;
        }
        return target.getLength();
    }

    @Override
    public void insertText(int offset, @NotNull String toInsert) {
        if (isPendingInsertion && offset == pending.getFirst()) {
            pending = new Pair(offset, toInsert + pending.getSecond());
        } else {
            if (isPendingInsertion) {
                target.insertText(pending.getFirst(), pending.getSecond());
            }
            pending = new Pair(offset, toInsert);
        }
        isPendingInsertion = true;
    }

    @Override
    public void deleteText(int offset, int size) {
        if (isPendingInsertion) {
            target.insertText(pending.getFirst(), pending.getSecond());
            isPendingInsertion = false;
        }
        target.deleteText(offset, size);
    }

    @Override
    public String toString() {
        if (isPendingInsertion) {
            target.insertText(pending.getFirst(), pending.getSecond());
            isPendingInsertion = false;
        }
        return target.toString();
    }

    @Override
    public int compareTo(Object other) {
        String thisString = this.toString();
        String otherString = other.toString();
        return thisString.compareTo(otherString);
    }
}
