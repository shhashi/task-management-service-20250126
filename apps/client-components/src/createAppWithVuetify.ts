import type { Component } from 'vue';
import { createApp } from 'vue';
import { createVuetify } from 'vuetify';
import 'vuetify/styles';

const vuetify = createVuetify();

export function createVuetifyApp(component: unknown) {
  return createApp(<Component>component).use(vuetify);
}
