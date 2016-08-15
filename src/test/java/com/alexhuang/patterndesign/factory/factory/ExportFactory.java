package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.ExportFile;

public interface ExportFactory {
    public ExportFile factory(String type);
}
