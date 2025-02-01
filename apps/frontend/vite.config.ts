import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path';
import { globSync } from 'node:fs';
import tailwindcss from '@tailwindcss/vite'; // エントリーポイントの定義

// エントリーポイントの定義
const entryPoints: Record<string, string> = {};
const files = globSync('./src/app/*.ts');
files.forEach((file: string) => {
  const name = file.split('/').pop()!.replace('.ts', '');
  entryPoints[name] = path.resolve(__dirname, file);
});

// https://vite.dev/config/
export default defineConfig({
  plugins: [tailwindcss(), vue()],
  build: {
    emptyOutDir: true,
    rollupOptions: {
      input: entryPoints,
      output: {
        entryFileNames: '[name].js',
      },
    },
  },
});
