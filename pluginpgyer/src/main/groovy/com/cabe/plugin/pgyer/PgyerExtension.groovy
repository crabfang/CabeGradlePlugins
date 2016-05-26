package com.cabe.plugin.pgyer

import org.gradle.api.NamedDomainObjectContainer

public class PgyerExtension {
    final private NamedDomainObjectContainer<ApkTarget> deploygateApks
    String _api_key
    String uKey

    public PgyerExtension(NamedDomainObjectContainer<ApkTarget> apks) {
        deploygateApks = apks
    }

    public apks(Closure closure) {
        deploygateApks.configure(closure)
    }
}
