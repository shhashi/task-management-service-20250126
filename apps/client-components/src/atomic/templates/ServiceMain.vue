<template>
  <ServiceMainBar
    v-model:open="navigationOpen"
    :title="title"
    @click:open-project-creation-modal-button="projectCreationModalOpen = true"
  ></ServiceMainBar>
  <ProjectCreationModal
    v-model:open="projectCreationModalOpen"
    @created-project="openProject"
  ></ProjectCreationModal>
  <ServiceMainSideNavigation
    :navigation="navigationOpen"
    @click:button="changeViewType"
  ></ServiceMainSideNavigation>
  <v-main>
    <slot />
  </v-main>
</template>

<script setup lang="ts">
import ServiceMainBar from "@/atomic/organisms/bar/ServiceMainBar.vue";
import ServiceMainSideNavigation from "@/atomic/organisms/navigation/ServiceMainSideNavigation.vue";
import { ref } from "vue";
import ProjectCreationModal from "@/atomic/organisms/modal/ProjectCreationModal.vue";
import { type Project, type ViewType, ViewTypes } from "@/lib/view/ViewType";

const navigationOpen = ref(true);
const projectCreationModalOpen = ref(false);

type Props = {
  title: string;
};
const { title } = defineProps<Props>();

type Emits = {
  (event: "change:viewType", value: ViewType): void;
};
const emits = defineEmits<Emits>();

function changeViewType(viewTypes: ViewTypes): void {
  const viewType: ViewType = {
    viewType: viewTypes,
  };
  emits("change:viewType", viewType);
}

function openProject(value: Project) {
  const viewType: ViewType = {
    viewType: ViewTypes.Project,
    project: value,
  };
  emits("change:viewType", viewType);
}
</script>
