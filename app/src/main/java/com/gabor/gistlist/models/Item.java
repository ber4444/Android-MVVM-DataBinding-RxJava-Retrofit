package com.gabor.gistlist.models;

import java.util.Iterator;

/**
 * Created by gabor on 12/3/17.
 */

public class Item {
    public String avatarUrl;
    public String filename;
    public String type;
    public String language;
    public Integer size;

    public Item(Gist gist) {
        this.avatarUrl = gist.owner.avatarUrl;
        Iterator<AttachedFile> iterator = gist.files.values().iterator();
        if (iterator.hasNext()) {
            AttachedFile file = iterator.next();
            this.filename = file.filename;
            this.type = file.type;
            this.language = file.language;
            this.size = file.size;
        }
    }
}
