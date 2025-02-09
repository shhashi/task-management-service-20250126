// Plugins
import Components from 'unplugin-vue-components/vite';
import Vue from '@vitejs/plugin-vue';
import Vuetify, { transformAssetUrls } from 'vite-plugin-vuetify';
import ViteFonts from 'unplugin-fonts/vite';
import VueRouter from 'unplugin-vue-router/vite';

// Utilities
import { defineConfig } from 'vite';
import { fileURLToPath, URL } from 'node:url';

import path from 'path';
import { globSync } from 'node:fs';

// ビルド対象となる HTML ファイルの対応表
const entryPoints: Record<string, string> = {};
const files = globSync('./src/pages/**/*.html');
files.forEach((file: string) => {
  console.log(file.split('/').pop()!.replace('.html', ''));
  const paths = file.split('/');
  const fileName = paths.pop()!.replace('.html', '');
  const pageName = paths.pop()!;
  entryPoints[fileName + '/' + pageName] = path.resolve(__dirname, file);
});

// https://vitejs.dev/config/
export default defineConfig({
  root: 'src/',
  publicDir: path.resolve(__dirname, 'public'),
  plugins: [
    VueRouter(),
    Vue({
      template: { transformAssetUrls }
    }),
    // https://github.com/vuetifyjs/vuetify-loader/tree/master/packages/vite-plugin#readme
    Vuetify({
      autoImport: true
      // styles: {
      //   configFile: 'src/styles/settings.scss'
      // }
    }),
    Components(),
    ViteFonts({
      google: {
        families: [{
          name: 'Roboto',
          styles: 'wght@100;300;400;500;700;900'
        }]
      }
    })
  ],
  define: { 'process.env': {} },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
    extensions: [
      '.js',
      '.json',
      '.jsx',
      '.mjs',
      '.ts',
      '.tsx',
      '.vue'
    ]
  },
  server: {
    port: 3000
  },
  css: {
    preprocessorOptions: {
      sass: {
        api: 'modern-compiler'
      }
    }
  },
  build: {
    outDir: path.resolve(__dirname, '../web/src/main/resources/static'),
    emptyOutDir: false,
    rollupOptions: {
      input: entryPoints
    }
  }
});
