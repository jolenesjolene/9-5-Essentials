package net.jolene.ninetofiveessentials.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.client.gl.RenderPipelines;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Arrays;

@Mixin(RenderPipeline.class)
public class RenderPipelineMixin {
    @Unique private static boolean hasModified = false;
    @ModifyVariable(method = "builder", at = @At("HEAD"), argsOnly = true)
    private static RenderPipeline.Snippet[] modifySnippets(RenderPipeline.Snippet[] snippets)
    {
        if (hasModified)
            return snippets;
        if (snippets.length == 1)
        {
            if (snippets[0].equals(RenderPipelines.TRANSFORMS_PROJECTION_FOG_SNIPPET))
            {
                snippets = new RenderPipeline.Snippet[]{snippets[0], RenderPipelines.GLOBALS_SNIPPET};
                hasModified = true;
            }
        }

        return snippets;
    }
}