package de.novakuso.spaceinvadors;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.util.List;

public class Util {
    public static final Moshi MOSHI = new Moshi.Builder().build();

    public static final JsonAdapter<Level> LEVEL_ADAPTER = MOSHI.adapter(Level.class);

    public static final JsonAdapter<List<Object>> JSON_LIST_ADAPTER = MOSHI.adapter(Types.newParameterizedType(List.class, Object.class));
}
