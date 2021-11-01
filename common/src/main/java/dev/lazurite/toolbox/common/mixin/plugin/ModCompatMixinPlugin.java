package dev.lazurite.toolbox.common.mixin.plugin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModCompatMixinPlugin implements IMixinConfigPlugin {
    protected List<ModCompat> modCompats;

    @Override
    public void onLoad(String mixinPackage) {
        this.modCompats = new ArrayList<>();
    }

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        for (var modCompat : this.modCompats) {
            if (modCompat.isPresent() && modCompat.isIncompatibleMixin(mixinClassName)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() {
        List<String> additionalMixins = new ArrayList<>();

        this.modCompats.forEach(
                modCompat -> {
                    if (modCompat.isPresent()) {
                        additionalMixins.addAll(modCompat.getAdditionalMixins());
                    }
                }
        );

        return additionalMixins.isEmpty() ? null : additionalMixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

}