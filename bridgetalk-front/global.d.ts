/// <reference types="webpack-env" />

declare function require(context: string): {
  keys: () => string[];
  <T>(key: string): T;
};
