/*
 * Copyright (c) 2016 Lucko (Luck) <luck@lucko.me>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.luckperms.calculators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.lucko.luckperms.api.Tristate;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Map;
import java.util.function.Supplier;

@AllArgsConstructor
public class AttachmentProcessor implements PermissionProcessor {

    @Getter
    private final Supplier<Map<String, PermissionAttachmentInfo>> map;

    @Override
    public Tristate hasPermission(String permission) {
        Map<String, PermissionAttachmentInfo> m = map.get();
        if (m == null) {
            return Tristate.UNDEFINED;
        }

        if (m.containsKey(permission)) {
            return Tristate.fromBoolean(m.get(permission).getValue());
        }
        return Tristate.UNDEFINED;
    }

}