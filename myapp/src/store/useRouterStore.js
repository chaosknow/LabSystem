import { defineStore } from "pinia";
import { ref } from "vue";
export const useRouterStore = defineStore("router", () => {
  const isGetterRouter = ref(false);

  const changeRouter = (value) => {
    isGetterRouter.value = value;
  };

  return { isGetterRouter, changeRouter };
});
