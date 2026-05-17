package com.cukkoo.keepwalking.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin extends ClientInput {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void keepwalking$tick(CallbackInfo ci) {
        ci.cancel();

        Minecraft client = Minecraft.getInstance();
        Options opts = client.options;

        boolean forward, back, left, right, jump, shift, sprint;

        if (client.screen instanceof AbstractContainerScreen<?>) {
            Window window = client.getWindow();
            forward = keepwalking$isKeyDown(window, opts.keyUp);
            back    = keepwalking$isKeyDown(window, opts.keyDown);
            left    = keepwalking$isKeyDown(window, opts.keyLeft);
            right   = keepwalking$isKeyDown(window, opts.keyRight);
            jump    = keepwalking$isKeyDown(window, opts.keyJump);
            shift   = keepwalking$isKeyDown(window, opts.keyShift);
            sprint  = keepwalking$isKeyDown(window, opts.keySprint);
        } else {
            forward = opts.keyUp.isDown();
            back    = opts.keyDown.isDown();
            left    = opts.keyLeft.isDown();
            right   = opts.keyRight.isDown();
            jump    = opts.keyJump.isDown();
            shift   = opts.keyShift.isDown();
            sprint  = opts.keySprint.isDown();
        }

        this.keyPresses = new Input(forward, back, left, right, jump, shift, sprint);

        float forwardImpulse  = keepwalking$impulse(forward, back);
        float sidewaysImpulse = keepwalking$impulse(left, right);
        this.moveVector = new Vec2(sidewaysImpulse, forwardImpulse).normalized();
    }

    @Unique
    private static float keepwalking$impulse(boolean positive, boolean negative) {
        return (positive ? 1.0F : 0.0F) - (negative ? 1.0F : 0.0F);
    }

    @Unique
    private static boolean keepwalking$isKeyDown(Window window, net.minecraft.client.KeyMapping mapping) {
        InputConstants.Key boundKey = ((KeyMappingAccessor) mapping).keepwalking$getBoundKey();
        return InputConstants.isKeyDown(window, boundKey.getValue());
    }
}
