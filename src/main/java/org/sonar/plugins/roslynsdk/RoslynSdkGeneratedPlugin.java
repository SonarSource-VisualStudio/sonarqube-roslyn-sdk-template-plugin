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
import org.sonar.api.Plugin;

public class RoslynSdkGeneratedPlugin implements Plugin {

  private static final RoslynSdkConfiguration config = new RoslynSdkConfiguration();

  @Override
  public String toString() {
    return config.mandatoryProperty("PluginKeyDifferentiator");
  }

  @Override
  public void define(Context context) {
    List<Object> extensions = new ArrayList<>();

    extensions.add(RoslynSdkConfiguration.class);
    extensions.add(RoslynSdkRulesDefinition.class);

    extensions.addAll(new RoslynSdkPluginProperties(config).getProperties());

    context.addExtensions(Collections.unmodifiableList(extensions));
  }

}
