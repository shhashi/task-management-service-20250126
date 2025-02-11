import type { Component } from "vue";
import { createApp } from "vue";
import { createVuetify } from "vuetify";
import "vuetify/styles";

const vuetify = createVuetify();

export function createVuetifyApp(
  component: unknown,
  rootProps?: Record<string, unknown> | null,
) {
  return createApp(<Component>component, rootProps).use(vuetify);
}
