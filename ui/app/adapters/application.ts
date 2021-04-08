/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
import BaseJsonAdapter from 'navi-core/adapters/base-json-adapter';

export default class Application extends BaseJsonAdapter {}

// DO NOT DELETE: this is how TypeScript knows how to look up your adapters.
declare module 'ember-data/types/registries/adapter' {
  export default interface AdapterRegistry {
    application: Application;
  }
}
