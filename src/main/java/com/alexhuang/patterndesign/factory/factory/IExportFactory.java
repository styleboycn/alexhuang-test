package com.alexhuang.patterndesign.factory.factory;

import com.alexhuang.patterndesign.factory.export.IExportFile;

public interface IExportFactory {
    public IExportFile factory(String type);
}
