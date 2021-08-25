package dev.lazurite.toolbox.api.mixin.plugin;

import net.fabricmc.loader.api.FabricLoader;

import java.util.HashSet;
import java.util.Set;

public class ModCompat {
    private final String id;

    private final Set<String> incompatibleMixins;
    private final Set<String> additionalMixins;

    public ModCompat(String id) {
        this.id = id;

        this.incompatibleMixins = new HashSet<>();
        this.additionalMixins = new HashSet<>();
    }

    public boolean isPresent() {
        return FabricLoader.getInstance().isModLoaded(this.id);
    }

    public ModCompat addIncompatibleMixin(String mixin) {
        this.incompatibleMixins.add(mixin);
        return this;
    }

    public Set<String> getIncompatibleMixins() {
        return this.incompatibleMixins;
    }

    public boolean isIncompatibleMixin(String mixin) {
        return this.incompatibleMixins.contains(mixin);
    }

    public ModCompat addAdditionalMixin(String additionalMixin) {
        this.additionalMixins.add(additionalMixin);
        return this;
    }

    public Set<String> getAdditionalMixins() {
        return this.additionalMixins;
    }

}
