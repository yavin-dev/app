/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
import config from 'yavin-ui/config/environment';
import { mergeWith } from 'lodash-es';

declare global {
  interface Window {
    NAVI_APP: Record<string, unknown>;
  }
}

export function initialize(): void {
  // Navi specific configuration
  mergeWith(config.navi, window.NAVI_APP, (a: unknown, b: unknown) => {
    Array.isArray(a) && Array.isArray(b) ? [...a, ...b] : undefined;
  });
}

export default {
  name: 'config',
  initialize,
};
