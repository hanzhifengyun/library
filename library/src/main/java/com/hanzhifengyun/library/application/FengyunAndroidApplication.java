/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hanzhifengyun.library.application;


import org.litepal.LitePalApplication;


/**
 * Android Main Application
 */
public abstract class FengyunAndroidApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化依赖注入
        initInjector();

        initEvent();
    }

    public abstract void initInjector();


    protected abstract void initEvent();
}
