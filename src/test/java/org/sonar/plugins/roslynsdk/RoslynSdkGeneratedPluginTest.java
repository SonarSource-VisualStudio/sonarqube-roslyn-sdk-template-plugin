/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2021 SonarSource SA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.sonar.plugins.roslynsdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.fest.assertions.Assertions.assertThat;

public class RoslynSdkGeneratedPluginTest {

  @Test
  public void getExtensions() {
    RoslynSdkGeneratedPlugin plugin = new RoslynSdkGeneratedPlugin();
    Plugin.Context context = new Plugin.Context(SonarRuntimeImpl.forSonarQube(Version.create(6, 7), SonarQubeSide.SCANNER));
    plugin.define(context);

    List extensions = context.getExtensions();
    assertThat(extensions).hasSize(9);

    Class<?>[] expectedExtensions = new Class<?>[] {
      RoslynSdkConfiguration.class,
      RoslynSdkRulesDefinition.class
    };

    assertThat(nonProperties(extensions)).contains(expectedExtensions);
    assertThat(extensions).hasSize(
      expectedExtensions.length
        + new RoslynSdkPluginProperties(new RoslynSdkConfiguration()).getProperties().size());
  }

  @Test
  public void pico_container_key_differentiator() {
    assertThat(new RoslynSdkGeneratedPlugin().toString()).isEqualTo("example");
  }

  private static List nonProperties(List extensions) {
    List props = new ArrayList<>();
    for (Object extension : extensions) {
      if (!(extension instanceof PropertyDefinition)) {
        props.add(extension);
      }
    }
    return Collections.unmodifiableList(props);
  }

}
